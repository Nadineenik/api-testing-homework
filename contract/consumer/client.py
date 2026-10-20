import requests

class UserClient:
    def __init__(self, base_url: str):
        self.base_url = base_url

    def get_user(self, user_id: int) -> dict:
        resp = requests.get(f"{self.base_url}/users/{user_id}")
        resp.raise_for_status()
        return resp.json()