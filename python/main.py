# main.py
from fastapi import FastAPI, Query

from exception_handler import global_exception_handler
from helpers import get_users, get_movies, get_reviews
from recommendation import hybrid_recommendation

app = FastAPI()

app.exception_handler(Exception)(global_exception_handler)


@app.get("/cause-error")
def cause_error():
    raise ValueError("This is a test error!")


@app.get("/recommendations")
def get_recommendations(page: int = Query(1, ge=1)):
    users = get_users()
    movies = get_movies()
    reviews = get_reviews()

    recommended_ids = hybrid_recommendation(users, movies, reviews)

    return {"recommendedMovieIds": recommended_ids}


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="localhost", port=8000)
