def test_get_nonexistent_pet_returns_404(api, base_url):
    resp = api.get(f"{base_url}/pet/999999999")
    assert resp.status_code == 404

def test_create_pet_without_required_name_returns_400(api, base_url):
    resp = api.post(f"{base_url}/pet", json={"status": "available", "photoUrls": []})
    # Petstore может вернуть 400 или 405 — проверяем, что это клиентская ошибка
    assert 400 <= resp.status_code < 500