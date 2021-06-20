import uvicorn
from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from pony import orm

from objects import Computer, Display

app = FastAPI()

app.mount('/data', StaticFiles(directory='data'), name='static_data')


@app.get("/")
def root():
    return {"message": "Hello World"}

@app.get("/display")
def display_list():
    with orm.db_session:
        return list(item.to_dict() for item in Display.select())

@app.get("/computer")
def computer_list():
    with orm.db_session:
        return list(item.to_dict() for item in Computer.select())

if __name__ == '__main__':
    uvicorn.run(app, host='0.0.0.0')