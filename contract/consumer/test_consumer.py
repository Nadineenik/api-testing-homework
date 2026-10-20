import pytest
from pact.v2 import Consumer, Provider
from client import UserClient

@pytest.fixture
def pact():
    pact = Consumer("UserWebApp").has_pact_with(Provider("UserService"), pact_dir="./pacts")
    pact.start_service()
    yield pact
    pact.stop_service()

def test_get_existing_user(pact):
    user_id = 123
    expected = {"id": user_id, "name": "Alice", "email": "alice@example.com"}

    (
        pact
        .given("user 123 exists")
        .upon_receiving("a request for user 123")
        .with_request("GET", f"/users/{user_id}")
        .will_respond_with(200, body=expected)
    )

    with pact:
        client = UserClient(pact.uri)
        result = client.get_user(user_id)
        assert result == expected