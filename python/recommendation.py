from typing import List

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity


def weighted_rating(rating: float) -> float:
    if rating < 2.5:
        return -3
    else:
        return rating


def calculate_genre_similarity(movies: List[dict]) -> dict:
    movie_ids = [movie["id"] for movie in movies]

    genre_texts = []
    for movie in movies:
        genres = movie.get("genres", [])
        if not isinstance(genres, list):
            genres = []
        genre_texts.append(" ".join(genres))

    vectorizer = TfidfVectorizer()
    genre_matrix = vectorizer.fit_transform(genre_texts)

    similarity_matrix = cosine_similarity(genre_matrix)

    genre_similarities = {
        movie_ids[i]: {
            movie_ids[j]: similarity_matrix[i, j]
            for j in range(len(movie_ids))
            if i != j
        }
        for i in range(len(movie_ids))
    }

    return genre_similarities

#
# def hybrid_recommendation(users: List[dict], movies: List[dict], reviews: List[dict]) -> List[str]:
#     # Khởi tạo điểm và số lượng đánh giá cho mỗi phim
#     movie_scores = {movie["id"]: 0 for movie in movies}
#     rating_counts = {movie["id"]: 0 for movie in movies}
#
#     # Tính điểm dựa trên đánh giá
#     for review in reviews:
#         m_id = review.get("movieId")
#         rating = review.get("rating")
#
#         if m_id not in movie_scores:
#             continue
#
#         try:
#             rating = float(rating)
#         except ValueError:
#             continue
#
#         weighted_r = weighted_rating(rating)
#         movie_scores[m_id] += weighted_r
#         rating_counts[m_id] += 1
#
#     # Tính điểm trung bình cho mỗi phim
#     for m_id in movie_scores.keys():
#         if rating_counts[m_id] > 0:
#             movie_scores[m_id] /= rating_counts[m_id]
#         else:
#             movie_scores[m_id] = 0
#
#     # Sắp xếp lại danh sách sau khi tính điểm trung bình
#     sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)
#     movie_scores = dict(sorted_movies)
#     print("movie_scores 1", movie_scores)
#
#     # Tính điểm dựa trên năm phát hành (nếu cần)
#     # for movie in movies:
#     #     release_year = movie.get("releaseYear", 0)
#     #     if release_year >= 2023:
#     #         movie_scores[movie["id"]] += 4
#
#     # Sắp xếp lại danh sách sau khi cộng điểm năm phát hành
#     # sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)
#     # movie_scores = dict(sorted_movies)
#
#     genre_similarities = calculate_genre_similarity(movies)
#     for movie_id in movie_scores.keys():
#         for related_movie, similarity in genre_similarities.get(movie_id, {}).items():
#             print("TOP", related_movie, similarity, movie_scores)
#             movie_scores[movie_id] += 0.2
#
#     # Sắp xếp lại danh sách sau khi cộng điểm tương đồng thể loại
#     sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)
#     movie_scores = dict(sorted_movies)
#     print("movie_scores 2", movie_scores)
#     # Lấy top 7 phim có điểm cao nhất
#     recommended_ids = [movie_id for movie_id, _ in sorted_movies[:7]]
#
#     return recommended_ids


def hybrid_recommendation(users: List[dict], movies: List[dict], reviews: List[dict]) -> List[str]:
    # Khởi tạo điểm và số lượng đánh giá cho mỗi phim
    movie_scores = {movie["id"]: 0 for movie in movies}
    rating_counts = {movie["id"]: 0 for movie in movies}

    # Tính điểm dựa trên đánh giá
    for review in reviews:
        m_id = review.get("movieId")
        rating = review.get("rating")

        if m_id not in movie_scores:
            continue

        try:
            rating = float(rating)
        except ValueError:
            continue

        weighted_r = weighted_rating(rating)
        movie_scores[m_id] += weighted_r
        rating_counts[m_id] += 1

    # Tính điểm trung bình cho mỗi phim
    for m_id in movie_scores.keys():
        if rating_counts[m_id] > 0:
            movie_scores[m_id] /= rating_counts[m_id]
        else:
            movie_scores[m_id] = 0

    # Sắp xếp lại danh sách sau khi tính điểm trung bình
    sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)
    movie_scores = dict(sorted_movies)
    print("movie_scores 1", movie_scores)

    # Tính điểm dựa trên năm phát hành (nếu cần)
    for movie in movies:
        release_year = movie.get("releaseYear", 0)
        if release_year >= 2023:
            movie_scores[movie["id"]] += 4

    # Sắp xếp lại danh sách sau khi cộng điểm năm phát hành
    sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)
    movie_scores = dict(sorted_movies)

    # Lọc ra các phim có đánh giá cao (điểm trung bình >= 2.5)
    high_rated_movies = {m_id for m_id, score in movie_scores.items() if score >= 2.5}

    # Lấy thể loại của các phim có đánh giá cao
    high_rated_genres = []
    for movie in movies:
        if movie["id"] in high_rated_movies:
            # Cắt chuỗi genres thành mảng và thêm vào high_rated_genres
            genres = movie.get("genres", "")
            if isinstance(genres, list) and len(genres) > 0:
                # Nếu genres là danh sách nhưng chứa chuỗi duy nhất, cần tách ra
                genres = genres[0].split(",")
            genres = [genre.strip() for genre in genres]
            high_rated_genres.extend(genres)

    # Duyệt qua tất cả các phim và cộng điểm nếu có cùng thể loại với phim có đánh giá cao
    for movie in movies:
        m_id = movie["id"]
        genres = movie.get("genres", [])
        # Kiểm tra xem phim có cùng thể loại với phim có đánh giá cao không
        if isinstance(genres, list) and len(genres) > 0:
            genres = genres[0].split(",")  # Chuyển thành danh sách thực sự

        genres = [genre.strip() for genre in genres]  # Xử lý khoảng trắng

        if any(genre in high_rated_genres for genre in genres):  # Kiểm tra thể loại có nằm trong danh sách không
            movie_scores[m_id] += 2
            print(movie_scores[m_id])
            print(high_rated_genres)

    # Sắp xếp lại danh sách sau khi cộng điểm
    sorted_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)
    movie_scores = dict(sorted_movies)
    print("movie_scores 2", movie_scores)

    # Lấy top 7 phim có điểm cao nhất
    recommended_ids = [movie_id for movie_id, _ in sorted_movies[:7]]

    return recommended_ids
