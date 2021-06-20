import requests
from bs4 import BeautifulSoup

class SoupFactory:
    default_request_headers = {
        'User-Agent': 'Mozilla/5.0',
    }

    base_url = "https://www.e-katalog.ru/"

    def __init__(self, headers=None):
        self.request_headers = headers or self.default_request_headers

    def make_request(self, url, middleware=None, **kwargs):
        r = requests.get(
            url=url,
            headers=self.request_headers,
            **kwargs
        )
        if middleware:
            data = middleware(r)
        else:
            data = r.text
        return BeautifulSoup(data, features="html5lib")
    
    def download_image(self, url, filename):
        r = requests.get(
            url=url,
            headers=self.request_headers
        )
        with open(filename, 'wb') as f:
            for chunk in r:
                f.write(chunk)
        return filename
