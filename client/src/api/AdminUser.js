import http from "./http";

const URL_AdminUser = "admin/user";

export const adminUserApi = {
  getAllUser(page, size) {
    return http.get(`${URL_AdminUser}?page=${page}&size=${size}`);
  },
  getUserById(id) {
    return http.get(`${URL_AdminUser}/${id}`);
  },
  createAdminUser(user) {
    return http.delete(`${URL_AdminUser}`, user);
  },
  deleteAdminUser(id) {
    return http.delete(`${URL_AdminUser}/${id}`);
  },
  EditAdminUser(id, user) {
    return http.put(`${URL_AdminUser}/${id}`, user);
  },
};
