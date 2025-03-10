import http from "./http";

const URL_Movie = "admin/movie";

export const adminMovieApi = {
  getAllMovie(page, size) {
    return http.get(`${URL_Movie}?page=${page}&size=${size}`);
  },
  getById(id) {
    return http.get(`${URL_Movie}/${id}`);
  },
  createMovie(movie) {
    return http.post(`${URL_Movie}`, movie);
  },
  updateMovie(id, movie) {
    return http.put(`${URL_Movie}/${id}`, movie);
  },
  deleteMovie(id) {
    return http.delete(`${URL_Movie}/${id}`);
  },
  // EditAdminUser(id, userName, email, password, profilePicture, role) {
  //   return http.put(`${URL_Movie}/${id}`, {
  //     userName: userName,
  //     email: email,
  //     password: password,
  //     profilePicture: profilePicture,
  //     role: role,
  //   });
  // },
};
