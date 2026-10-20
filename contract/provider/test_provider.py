from pact import Verifier
import pytest

def test_provider_against_contract():
    # В v3 Verifier принимает имя провайдера и использует цепочку методов
    verifier = (
        Verifier("UserService")  # Имя провайдера, как в контракте
        .add_source("./pacts/")  # Папка с файлами контрактов
        .add_transport(url="http://localhost:8000")  # URL вашего запущенного сервиса
    )
    
    # Метод verify() выбросит исключение, если проверка не пройдёт
    verifier.verify()