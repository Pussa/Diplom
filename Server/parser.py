
from display import Display
from computer import Computer
from pathlib import Path
from network import SoupFactory

class App:
    def __init__(self):
        self.screen_list_url = SoupFactory.base_url + "list/157"
        self.computer_list_url = SoupFactory.base_url + "list/170"
        self.soup_factory = SoupFactory()

        self.displays = []
        self.computers = []

        self.image_path = Path('./data/img')
        self.image_path.mkdir(parents=True, exist_ok=True)
    
    def run(self):
        print("=[ Displays ]=")
        self.parse_displays(pages=20)
        print("=[ Computers ]=")
        self.parse_computers(pages=8)
    
    def parse_displays(self, pages=1):
        page = 0

        while page < pages:
            print(f"Fetching page #{page}...")
            url = f"{self.screen_list_url}/{page}/"

            soup = self.soup_factory.make_request(url)
            item_list = soup.find("form", id="list_form1")
            items = item_list.contents
            
            print("Parsing data...")

            for item in items:
                try:
                    if ('id' not in item.attrs) or ('class' in item.attrs and 'list-more' in item['class']):
                        continue
                    row = item.div.table.tbody.tr

                    screen = Display.parse_from_row(row, self.soup_factory)

                    self.displays.append(screen)
                    
                    print(str(screen))

                    screen.save_to_db(image_dir=self.image_path)
                except ValueError:
                    pass
            
            page += 1
    
    def parse_computers(self, pages=1):
        page = 0

        while page < pages:
            print(f"Fetching page #{page}...")
            url = f"{self.computer_list_url}/{page}/"

            soup = self.soup_factory.make_request(url)
            item_list = soup.find("form", id="list_form1")
            items = item_list.find_all('div', class_='list-item--goods-group')

            print("Parsing data...")

            for item in items:
                try:
                    computer = Computer.parse_from_row(item, self.soup_factory)

                    self.computers.append(computer)

                    print(str(computer))

                    computer.save_to_db(image_dir=self.image_path)
                except ValueError:
                    pass
            page += 1
        


if __name__ == '__main__':
    app = App()
    try:
        input("Press any key to start the parser...")
        app.run()
    except EOFError:
        pass
