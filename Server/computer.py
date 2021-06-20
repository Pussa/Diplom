import re
import traceback
import uuid
import sys

from pony.orm import db_session, ObjectNotFound, select, count
from price import ComputerPrice
from network import SoupFactory
from objects import Computer as DBComputer, ComputerPrice as DBComputerPrice


class Computer:
    def __init__(self, id, name):
        self.id = id
        self.name = name

        self.cpu = None
        self.ram = None
        self.gpu = None
        self.storage = None

        self.image_url = None
        self.prices = []
        self.db_object = None

    def __str__(self):
        return f"Компьютер: {self.name} [id: {self.id}]"

    def get_full_description(self):
        base = f"""Компьютер: {self.name} [id: {self.id}]
{self.cpu}, {self.gpu}, RAM: {self.ram}, storage: {self.storage}\n\n"""

        return base + '\n'.join(str(price) for price in self.prices)

    @classmethod
    def parse_from_row(cls, row, soup_factory):

        name_common = row.find('td', class_='model-conf-title').a.span.get_text()

        price_row = row.find('table', class_='conf-table').find_all('tr')[0]
        cells = price_row.find_all('td')

        m = re.search(r"link-d-(\d+)", cells[1].a['id'])
        item_id = m.group(1)

        name_conf = cells[1].a.u.get_text()
        name = f"{name_common} ({name_conf})"

        item = cls(item_id, name)

        try:
            cpu_common = cells[3].span.get_text().strip()
            cpu_model = cells[4].span.get_text().strip()
            item.cpu = f"{cpu_common} {cpu_model}"
            item.gpu = cells[6].span.get_text().strip()
            item.ram = cells[5].span.get_text().strip()
            item.storage = cells[7].span.get_text().strip()

            item.image_url = soup_factory.base_url + row.find('table', class_='model-short-photo').find('img')['src']

            item.fetch_prices(soup_factory)
        except (AttributeError, ValueError):
            print(f"Couldn't parse item: {item}", file=sys.stderr)
            traceback.print_exc()
            print("\nParsed block:", file=sys.stderr)
            print(row.prettify(), end='\n\n', file=sys.stderr)
            raise ValueError
        return item

    def fetch_prices(self, soup_factory):
        url = "https://www.e-katalog.ru/mtools/mui_conf_prices.php"
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
        price_table = soup.find('table', class_='conf-prices-table')

        if price_table:
            self.prices = [
                ComputerPrice.parse_from_row(self, row)
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
                self.db_object = DBComputer[self.id]
            except ObjectNotFound:
                image_filename = self.save_image(image_dir)
                self.db_object = DBComputer(
                    id=self.id,
                    name=self.name,
                    cpu=self.cpu,
                    gpu=self.gpu,
                    ram=self.ram,
                    storage=self.storage,
                    image=str(image_filename)
                )
        for price in self.prices:
            price.save_to_db()
