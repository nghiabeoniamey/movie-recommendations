import http from "./http";

const URL_User = "user/user";

export const UserApi = {
  getById(id) {
    return http.get(`${URL_User}/${id}`);
  },
  deleteUser(id) {
    return http.delete(`${URL_User}/${id}`);
  },
  EditAdminUser(id, userName, email, password, profilePicture, role) {
    return http.put(`${URL_User}/${id}`, {
      userName: userName,
      email: email,
      password: password,
      profilePicture: profilePicture,
      role: role,
    });
  },
};
