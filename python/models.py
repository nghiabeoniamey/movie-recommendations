from pydantic import BaseModel
from typing import List

class User(BaseModel):
    id: int
    address: str

class Movie(BaseModel):
    id: int
    genres: List[str]
    releaseYear: int

class Review(BaseModel):
    userId: int
    movieId: int
    rating: float
    comment: str