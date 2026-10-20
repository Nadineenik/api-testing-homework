import pytest
from pact import Verifier
from main import app  # если используем ASGI, можно через uvicorn

def test_provider_against_contract():
    verifier = Verifier(provider="UserService", provider_base_url="http://localhost:8000")
    success, logs = verifier.verify_pacts("./pacts/userwebapp-userservice.json")
    assert success == 0, logs