import logging
import traceback
from typing import List

import requests
from fastapi import FastAPI, HTTPException, Query, Request
from fastapi.responses import JSONResponse
from pydantic import BaseModel

# start Logging

logging.basicConfig(
    level=logging.DEBUG,  # Mức log: DEBUG, INFO, WARNING, ERROR, CRITICAL
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
)

logger = logging.getLogger(__name__)
# end Logging

# start Exception Handler
app = FastAPI()

# Sử dụng logger đã cấu hình
logger = logging.getLogger("uvicorn.error")


@app.exception_handler(Exception)
async def global_exception_handler(request: Request, exc: Exception):
    # Log lỗi chi tiết bao gồm traceback
    logger.error(f"Unhandled exception: {exc}")
    logger.error(traceback.format_exc())

    # Trả về thông báo lỗi chung cho client
    return JSONResponse(
        status_code=500,
        content={"detail": "Internal Server Error"}
    )


# Ví dụ endpoint gây lỗi để kiểm tra
@app.get("/cause-error")
def cause_error():
    raise ValueError("This is a test error!")


# end Exception Handler

app = FastAPI()


# --- Định nghĩa model dữ liệu (dựa trên cấu trúc dữ liệu của bạn) ---
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


def get_api_java(end_point: str) -> str:
    return "http://localhost:6868/api/v1/connection/recommendation/" + end_point


# --- Các hàm trợ giúp để lấy dữ liệu từ Spring Boot API ---
def get_users() -> List[dict]:
    # Giả sử endpoint Spring Boot trả về danh sách người dùng
    response = requests.get(get_api_java("users"))
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu users")


def get_movies() -> List[dict]:
    # Giả sử endpoint Spring Boot trả về danh sách phim
    response = requests.get(get_api_java("movies"))
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu movies")


def get_reviews() -> List[dict]:
    # Giả sử endpoint Spring Boot trả về danh sách review
    response = requests.get(get_api_java("reviews"))
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu reviews")


# --- Thuật toán Hybrid Approach ---
def hybrid_recommendation(users: List[dict], movies: List[dict], reviews: List[dict]) -> List[str]:
    """
    Phương pháp này kết hợp:
    - Collaborative Filtering: Dựa vào rating từ bảng reviews.
    - Content-Based Filtering: Dựa vào thể loại và năm phát hành của phim.
    """

    # 1. Tạo ma trận phim - điểm số dựa trên đánh giá
    movie_scores = {movie["id"]: 0 for movie in movies}
    rating_counts = {movie["id"]: 0 for movie in movies}

    print(f"data movies: {movies}")
    print(f"data reviews: {reviews}")
    print(f"data users: {users}")

    # Xử lý dữ liệu từ reviews
    for review in reviews:
        m_id = review.get("movieId")
        rating = review.get("rating")

        if m_id not in movie_scores:
            print(f"Skipping review for unknown movieId: {m_id}")
            continue  # Bỏ qua nếu movieId không có trong danh sách movies

        try:
            rating = float(rating)  # Chuyển đổi rating thành số thực
        except ValueError:
            print(f"Skipping invalid rating: {rating}")
            continue

        movie_scores[m_id] += rating
        rating_counts[m_id] += 1

    # Tính điểm trung bình
    for m_id in movie_scores.keys():
        if rating_counts[m_id] > 0:
            movie_scores[m_id] /= rating_counts[m_id]
        else:
            movie_scores[m_id] = 0  # Nếu không có đánh giá, điểm mặc định là 0

    # 2. Content-Based Filtering: Cộng thêm điểm cho phim mới
    for movie in movies:
        if not isinstance(movie, dict):
            print(f"Skipping invalid movie data: {movie}")
            continue

        release_year = movie.get("releaseYear")
        if release_year is None or not isinstance(release_year, int):
            print(f"Skipping movie without valid releaseYear: {movie}")
            continue

        # Nếu phim phát hành sau năm 2015, cộng thêm điểm
        if release_year > 2015:
            movie_scores[movie["id"]] += 0.5

        # Xử lý danh sách thể loại
        genres = movie.get("genres", [])
        if isinstance(genres, list) and len(genres) > 0:
            genres = [g.strip() for genre in genres for g in genre.split(",")]
        else:
            genres = []

        # Nếu cần, có thể tính toán độ tương đồng thể loại tại đây.

    # 3. Kết hợp kết quả và sắp xếp phim theo điểm số
    sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)

    # Mặc định trả về 20 phim
    recommended_ids = [movie_id for movie_id, _ in sorted_movies[:20]]

    print(f"Recommended movies: {recommended_ids}")
    return recommended_ids


# --- Định nghĩa endpoint API ---
@app.get("/recommendations")
def get_recommendations(page: int = Query(1, ge=1)):
    """
    Endpoint trả về danh sách ID phim được gợi ý dựa trên thuật toán Hybrid Approach.
    Tham số:
      - page: số trang của kết quả (mặc định là 1)
    """
    # Lấy dữ liệu từ Spring Boot API
    users = get_users()  # Dữ liệu từ bảng User
    movies = get_movies()  # Dữ liệu từ bảng Movie
    reviews = get_reviews()  # Dữ liệu từ bảng Review

    # Gọi thuật toán Hybrid để lấy danh sách các ID phim gợi ý
    recommended_ids = hybrid_recommendation(users, movies, reviews)

    return {"recommendedMovieIds": recommended_ids}


# --- Chạy API ---
if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="localhost", port=8000)
