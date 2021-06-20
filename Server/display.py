import re
import traceback
import uuid
import sys

from pony.orm import db_session, ObjectNotFound
from price import DisplayPrice
from network import SoupFactory
from objects import Display as DBDisplay

class Display:
    def __init__(self, id, name):
        self.id = id
        self.name = name
        self.size = None
        self.resolution = None
        self.matrix_type = None
        self.frequency = None
        self.prices = []

        self.image_url = None
        self.image = None

        self.db_object = None

    def __str__(self):
        return f"Монитор: {self.name} [id: {self.id}]"
    
    def get_full_description(self):
        base = f"""Монитор {self.name} [id: {self.id}]
{self.size}, {self.resolution[0]}x{self.resolution[1]}, {self.matrix_type}, {self.frequency}\n\n"""

        return base + '\n'.join(str(price) for price in self.prices)

    
    @classmethod
    def parse_from_row(cls, row, soup_factory):
        item_id = row.find('a', class_='model-short-title')['data-idgood']
        info_cell = row.find(class_='model-short-info')
        prices_cell = row.find(class_='model-hot-prices-td')

        # print(info_cell.prettify())
        name = info_cell.find(class_='model-short-title').text
        info = info_cell.find(class_='m-s-f2').text
        image = row.find('table', class_='model-short-photo').find('img')

        item = cls(id=item_id, name=name)
        try:
            m = re.search(r"Экран:\b(\d+)", info)
            item.size = m.group(1)

            m = re.search(r"\b(\d+)x(\d+)\b", info)
            item.resolution = (m.group(1), m.group(2))

            m = re.search(r"Матрица:(\*?\w+)\b", info)
            item.matrix_type = m.group(1)

            m = re.search(r"(\d+)\sГц", info)
            item.frequency = m.group(1)

            item.image_url = SoupFactory.base_url + image['src']

            item.fetch_prices(soup_factory)

            return item
        except (ValueError, AttributeError):
            print(f"Couldn't parse item: {item}", file=sys.stderr)
            traceback.print_exc()
            print("\nParsed block:", file=sys.stderr)
            print(info_cell.find(class_='m-s-f2').prettify(), end='\n\n', file=sys.stderr)
            raise ValueError
        # except AttributeError:
        #     raise ValueError
    
    def fetch_prices(self, soup_factory):
        url = "https://www.e-katalog.ru/mtools/dot_output/mui_small_wherebuy.php"
        params = {
            'idg_': self.id,
            'ptype_': 'list',
            'num_offers_': 7,
            'view_': 'list'
        }

        def middleware(response):
            text = response.text
            new_text = text.replace("\\'", "'").replace('\\"', '"')
            return new_text[2:-2]

        soup = soup_factory.make_request(url, middleware=middleware, params=params)
        # print(soup.prettify())
        price_table = soup.find('table', class_='model-hot-prices')

        if price_table:
            self.prices = [
                DisplayPrice.parse_from_row(self, row) 
                for row in price_table.tbody.contents
            ]
        else:
            pass
    
    def save_image(self, image_dir):
        image_path = image_dir / (str(uuid.uuid4().hex) + '.jpg')
        SoupFactory().download_image(self.image_url, image_path)
        return image_path

    def save_to_db(self, image_dir):
        with db_session:
            try:
                self.db_object = DBDisplay[self.id]
            except ObjectNotFound:
                image_filename = self.save_image(image_dir)
                self.db_object = DBDisplay(
                    id=self.id,
                    name=self.name,
                    size=self.size,
                    resolutionX=self.resolution[0],
                    resolutionY=self.resolution[1],
                    matrix_type=self.matrix_type,
                    frequency=self.frequency,
                    image=str(image_filename)
                )
        for price in self.prices:
            price.save_to_db()