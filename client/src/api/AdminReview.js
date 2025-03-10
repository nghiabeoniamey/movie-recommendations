import http from "./http";

const URL_AdminReview = "admin/review";

export const adminReviewApi = {
  getAllReview(page, size) {
    return http.get(`${URL_AdminReview}?page=${page}&size=${size}`);
  },

  deleteAdminReview(id) {
    return http.delete(`${URL_AdminReview}/${id}`);
  },
};
