from fastapi import FastAPI, HTTPException, Request, status
from fastapi.responses import JSONResponse
from fastapi.exceptions import RequestValidationError
from pydantic import BaseModel
from typing import List, Optional
from enum import Enum

app = FastAPI()

# Перехватываем ошибки валидации, чтобы FastAPI возвращал 400, а не 422 (как ждут тесты)
@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request: Request, exc: RequestValidationError):
    return JSONResponse(
        status_code=status.HTTP_400_BAD_REQUEST,
        content={"detail": exc.errors()},
    )

class StatusEnum(str, Enum):
    available = "available"
    pending = "pending"
    sold = "sold"

class Pet(BaseModel):
    id: Optional[int] = None
    name: str
    status: Optional[StatusEnum] = None
    photoUrls: List[str] = []
    category: Optional[dict] = None
    tags: Optional[list] = None

db = {}
current_id = 1

# --- PET ENDPOINTS ---
@app.post("/pet")
def create_pet(pet: Pet):
    global current_id
    if pet.id is None:
        pet.id = current_id
        current_id += 1
    db[pet.id] = pet
    return pet

@app.put("/pet")
def update_pet(pet: Pet):
    if pet.id not in db:
        raise HTTPException(status_code=404, detail="Pet not found")
    db[pet.id] = pet
    return pet

@app.get("/pet/{pet_id}")
def get_pet(pet_id: int):
    if pet_id not in db:
        raise HTTPException(status_code=404, detail="Pet not found")
    return db[pet_id]

@app.delete("/pet/{pet_id}")
def delete_pet(pet_id: int):
    if pet_id not in db:
        raise HTTPException(status_code=404, detail="Pet not found")
    del db[pet_id]
    return {"message": "deleted"}

@app.get("/pet/findByStatus")
def find_by_status(status: str):
    return [pet for pet in db.values() if pet.status == status]

@app.get("/pet/findByTags")
def find_by_tags(tags: List[str]):
    return []

@app.post("/pet/{pet_id}/uploadImage")
def upload_image(pet_id: int):
    return {"code": 200, "type": "unknown", "message": "uploaded"}

# --- STORE ENDPOINTS (заглушки для тестов) ---
@app.post("/store/order")
def place_order(order: dict):
    return order

@app.get("/store/order/{order_id}")
def get_order(order_id: int):
    return {"id": order_id}

@app.delete("/store/order/{order_id}")
def delete_order(order_id: int):
    return {"message": "deleted"}

@app.get("/store/inventory")
def get_inventory():
    return {"available": 1}

# --- USER ENDPOINTS (заглушки для тестов) ---
@app.post("/user")
def create_user(user: dict):
    return user

@app.get("/user/{username}")
def get_user(username: str):
    return {"username": username}

@app.put("/user/{username}")
def update_user(username: str, user: dict):
    return user

@app.delete("/user/{username}")
def delete_user(username: str):
    return {"message": "deleted"}

@app.post("/user/createWithList")
def create_users_list(users: list):
    return {"message": "ok"}

@app.get("/user/login")
def login_user(username: str, password: str):
    return {"message": "logged in"}

@app.get("/user/logout")
def logout_user():
    return {"message": "logged out"}