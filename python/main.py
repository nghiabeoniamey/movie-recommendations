from fastapi import FastAPI, HTTPException, Query
from pydantic import BaseModel
from typing import List
import requests
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity

app = FastAPI()

# --- Định nghĩa model dữ liệu (dựa trên cấu trúc dữ liệu của bạn) ---
class User(BaseModel):
    id: int
    address: str

class Movie(BaseModel):
    id: int
    genres: List[str]
    release_year: int

class Review(BaseModel):
    user_id: int
    movie_id: int
    rating: float
    comment: str

# --- Các hàm trợ giúp để lấy dữ liệu từ Spring Boot API ---
def get_users() -> List[dict]:
    # Giả sử endpoint Spring Boot trả về danh sách người dùng
    response = requests.get("http://localhost:6868/api/v1/users")
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu users")

def get_movies() -> List[dict]:
    # Giả sử endpoint Spring Boot trả về danh sách phim
    response = requests.get("http://localhost:6868/api/v1/movies")
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu movies")

def get_reviews() -> List[dict]:
    # Giả sử endpoint Spring Boot trả về danh sách review
    response = requests.get("http://localhost:6868/api/v1/reviews")
    if response.status_code == 200:
        return response.json()
    else:
        raise HTTPException(status_code=500, detail="Lỗi khi lấy dữ liệu reviews")

# --- Thuật toán Hybrid Approach ---
def hybrid_recommendation(users: List[dict], movies: List[dict], reviews: List[dict],
                          page: int, page_size: int = 10) -> List[int]:
    """
    Phương pháp này kết hợp:
    - Collaborative Filtering: Dựa vào rating từ bảng reviews (có thể mở rộng với các đặc điểm của user như address)
    - Content-Based Filtering: Dựa vào các thuộc tính phim như thể loại và năm phát hành
    """

    # 1. Collaborative Filtering: Tạo ma trận người dùng - phim (giả sử dùng rating trung bình)
    movie_ids = [movie["id"] for movie in movies]
    movie_scores = {movie_id: 0 for movie_id in movie_ids}
    rating_counts = {movie_id: 0 for movie_id in movie_ids}

    # Cộng dồn rating từ review
    for review in reviews:
        m_id = review["movie_id"]
        movie_scores[m_id] += review["rating"]
        rating_counts[m_id] += 1

    # Tính điểm trung bình cho từng phim (nếu có review)
    for m_id in movie_ids:
        if rating_counts[m_id] > 0:
            movie_scores[m_id] /= rating_counts[m_id]
        else:
            movie_scores[m_id] = 0

    # 2. Content-Based Filtering: Tính điểm dựa trên thuộc tính của phim
    # Ví dụ: Cho thêm điểm nếu phim có năm phát hành gần đây và phù hợp thể loại
    # Giả sử: Nếu phim phát hành sau năm 2015 thì cộng thêm 0.5 điểm
    for movie in movies:
        if movie["release_year"] > 2015:
            movie_scores[movie["id"]] += 0.5

        # Nếu muốn tích hợp dựa trên thể loại, bạn có thể tạo vector nhị phân cho từng thể loại,
        # sau đó tính độ tương đồng giữa phim và sở thích của người dùng (ở đây có thể lấy trending genres từ reviews).
        # Đây là ví dụ đơn giản và có thể mở rộng:
        # movie_scores[movie["id"]] += (số lượng thể loại phù hợp) * hệ số trọng số

    # 3. Kết hợp kết quả:
    # Sắp xếp các phim dựa trên tổng điểm tính được
    sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)

    # Phân trang kết quả dựa trên page và page_size
    start = (page - 1) * page_size
    end = start + page_size
    recommended_ids = [movie_id for movie_id, score in sorted_movies[start:end]]

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
    users = get_users()      # Dữ liệu từ bảng User
    movies = get_movies()    # Dữ liệu từ bảng Movie
    reviews = get_reviews()  # Dữ liệu từ bảng Review

    # Gọi thuật toán Hybrid để lấy danh sách các ID phim gợi ý
    recommended_ids = hybrid_recommendation(users, movies, reviews, page)

    return {"recommended_movie_ids": recommended_ids}

# --- Chạy API ---
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="localhost", port=8000)
