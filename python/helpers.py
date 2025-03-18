# helpers.py
from typing import List

import requests
from fastapi import HTTPException


def get_api_java(end_point: str) -> str:
    return "http://localhost:6868/api/v1/connection/recommendation/" + end_point


def get_users() -> List[dict]:
    response = requests.get(get_api_java("users"))
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu users")


def get_movies() -> List[dict]:
    response = requests.get(get_api_java("movies"))
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu movies")


def get_reviews() -> List[dict]:
    response = requests.get(get_api_java("reviews"))
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu reviews")
