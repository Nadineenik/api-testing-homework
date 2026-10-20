def test_create_pet_with_invalid_status(api, base_url):
    resp = api.post(f"{base_url}/pet", json={
        "name": "Test",
        "status": "invalid_status",
        "photoUrls": []
    })
    assert resp.status_code in (400, 422)

def test_create_pet_with_wrong_type_for_id(api, base_url):
    resp = api.post(f"{base_url}/pet", json={"id": "not-a-number", "name": "Test"})
    assert resp.status_code in (400, 422)