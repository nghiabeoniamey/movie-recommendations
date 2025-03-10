import http from "./http";

const URL_UserReview = "user/review";

export const UserReviewApi = {
  getReviewByMovieId(movie, page, size) {
    return http.get(
      `${URL_UserReview}?movie=${movie}&page=${page}&size=${size}`
    );
  },
  createReview(movieId, rating, comment) {
    return http.post(`${URL_UserReview}`, {
      movieId: movieId,
      rating: rating,
      comment: comment,
    });
  },
  deleteReview(id) {
    return http.delete(`${URL_UserReview}/${id}`);
  },
};
