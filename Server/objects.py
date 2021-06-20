from pony import orm
from decimal import Decimal

db = orm.Database()

class Display(db.Entity):
    id = orm.PrimaryKey(int, auto=False)
    name = orm.Required(str)
    size = orm.Required(str)
    resolutionX = orm.Required(int)
    resolutionY = orm.Required(int)
    matrix_type = orm.Required(str)
    frequency = orm.Required(int)
    prices = orm.Set('DisplayPrice')
    
    image = orm.Optional(str)

    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'size': self.size,
            'resolution': f'{self.resolutionX}x{self.resolutionY}',
            'matrix_type': self.matrix_type,
            'frequency': self.frequency,
            'prices': [
                price.to_dict() for price in self.prices
            ],
            'image': self.image
        }

class DisplayPrice(db.Entity):
    item = orm.Required(Display)
    shop_name = orm.Required(str)
    value = orm.Required(Decimal)

    def to_dict(self):
        return {
            'shop': self.shop_name,
            'value': self.value
        }

class Computer(db.Entity):
    id = orm.PrimaryKey(int, auto=False)
    name = orm.Required(str)

    cpu = orm.Required(str)
    ram = orm.Required(str)
    gpu = orm.Required(str)
    storage = orm.Required(str)
    prices = orm.Set('ComputerPrice')

    image = orm.Optional(str)

    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'cpu': self.cpu,
            'ram': self.ram,
            'gpu': self.gpu,
            'storage': self.storage,
            'prices': [
                price.to_dict() for price in self.prices
            ],
            'image': self.image
        }

class ComputerPrice(db.Entity):
    item = orm.Required(Computer)
    shop_name = orm.Required(str)
    value = orm.Required(Decimal)

    def to_dict(self):
        return {
            'shop': self.shop_name,
            'value': self.value
        }

db.bind(provider='sqlite', filename='database.sqlite', create_db=True)

db.generate_mapping(create_tables=True)