def test_create_pet_returns_200(new_pet):
    # Убрали api, так как здесь мы не делаем запросов, 
    # а просто проверяем данные, которые вернула фикстура new_pet
    assert new_pet["id"] is not None
    assert new_pet["name"]
    assert new_pet["status"] == "available"

def test_get_pet_by_id(api, base_url, new_pet):
    # Теперь base_url доступна внутри функции
    resp = api.get(f"{base_url}/pet/{new_pet['id']}")
    assert resp.status_code == 200
    assert resp.json()["id"] == new_pet["id"]