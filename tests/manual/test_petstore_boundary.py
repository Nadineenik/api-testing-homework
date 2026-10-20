import pytest

# Добавили base_url в аргументы функции
@pytest.mark.parametrize("name", [
    "a",
    "a" * 255,
])
def test_pet_name_boundary_lengths(api, base_url, name): 
    resp = api.post(f"{base_url}/pet", json={"name": name, "photoUrls": []})
    assert resp.status_code in (200, 400)

def test_pet_id_negative_value(api, base_url):
    resp = api.get(f"{base_url}/pet/-1")
    assert resp.status_code == 404