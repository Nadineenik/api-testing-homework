# import pytest
# import requests
# from faker import Faker

# @pytest.fixture(scope="session")
# def base_url():
#     return "https://petstore3.swagger.io/api/v3"

# fake = Faker()

# @pytest.fixture
# def api():
#     session = requests.Session()
#     # Добавили User-Agent, чтобы сервер Petstore не блокировал нас ошибкой 403
#     session.headers.update({
#         "Content-Type": "application/json", 
#         "Accept": "application/json",
#         "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
#     })
#     yield session
#     session.close()

# # ВАЖНО: Добавили base_url в аргументы фикстуры
# @pytest.fixture
# def new_pet(api, base_url): 
#     """Создаёт питомца и удаляет его после теста."""
#     pet_data = {
#         "name": fake.first_name(),
#         "status": "available",
#         "photoUrls": ["https://example.com/photo.jpg"],
#     }
#     resp = api.post(f"{base_url}/pet", json=pet_data)
#     assert resp.status_code == 200
#     pet = resp.json()
#     yield pet
    
#     # И здесь тоже используем base_url
#     api.delete(f"{base_url}/pet/{pet['id']}")

import pytest
import requests
from faker import Faker

@pytest.fixture(scope="session")
def base_url():
    # Меняем на локальный сервер!
    return "http://127.0.0.1:8000"

fake = Faker()

@pytest.fixture
def api():
    session = requests.Session()
    # User-Agent больше не нужен, так как сервер локальный
    session.headers.update({"Content-Type": "application/json", "Accept": "application/json"})
    yield session
    session.close()

@pytest.fixture
def new_pet(api, base_url):
    pet_data = {
        "name": fake.first_name(),
        "status": "available",
        "photoUrls": ["https://example.com/photo.jpg"],
    }
    resp = api.post(f"{base_url}/pet", json=pet_data)
    assert resp.status_code == 200
    pet = resp.json()
    yield pet
    api.delete(f"{base_url}/pet/{pet['id']}")