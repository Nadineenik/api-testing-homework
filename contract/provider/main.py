from fastapi import FastAPI, HTTPException

app = FastAPI()
FAKE_DB = {123: {"id": 123, "name": "Alice", "email": "alice@example.com"}}

@app.get("/users/{user_id}")
def get_user(user_id: int):
    if user_id not in FAKE_DB:
        raise HTTPException(status_code=404)
    return FAKE_DB[user_id]