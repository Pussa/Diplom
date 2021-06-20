
import weakref
import re

from pony.orm import db_session, ObjectNotFound
from objects import DisplayPrice, ComputerPrice

class ItemPrice:
    db_class = None

    def __init__(self, item):
        self.item = item
        self.shop_name = None
        self.value = None

        self.db_object = None
    
    def __str__(self):
        return f"{self.value:>8} ₽ ({self.shop_name})"
    
    def save_to_db(self):
        if self.item is not None:
            with db_session(sql_debug=True):
                self.db_object = self.db_class.get(
                    item=self.item.id,
                    shop_name=self.shop_name
                )
                if self.db_object is None:
                    self.db_object = self.db_class(
                        item=self.item.id,
                        shop_name=self.shop_name,
                        value=self.value
                    )

class DisplayPrice(ItemPrice):
    db_class = DisplayPrice

    @classmethod
    def parse_from_row(cls, item, row):
        shop_cell = row.find('td', class_='model-shop-name')
        price_cell = row.find('td', class_='model-shop-price')

        price = cls(item)
        price.shop_name = shop_cell.div.a.u.text
        raw_price = price_cell.a.text
        price.value = int(re.sub('\D', '', raw_price))

        return price

class ComputerPrice(ItemPrice):
    db_class = ComputerPrice

    @classmethod
    def parse_from_row(cls, item, row):
        price = cls(item)
        
        price.shop_name = row.find('a', class_='it-shop').get_text().strip()
        
        raw_price = row.find('td', class_='conf-prices-price-big').a.get_text().strip()
        price.value = int(re.sub(r'[\Dр.]', '', raw_price))
        return price