import http from "./http";

export const URL_LOGIN = "auth/login";
export const URL_REGISTER = "auth/register";
export const URL_VERIFY = "auth/verify";
// export const URL_LOGOUT = "auth/logout";
export const URL_REFRESH = "auth/refresh";

const authApi = {
  registerAccount(body) {
    return http.post(URL_REGISTER, body);
  },
  login(body) {
    return http.post(URL_LOGIN, body);
  },
  verify(token) {
    return http.put(`${URL_VERIFY}?token=${token}`);
  },
  forgot(refreshtoken) {
    return http.put(`${URL_VERIFY}?token=${refreshtoken}`);
  },
  refresh(token) {
    return http.post(`${URL_VERIFY}`, token);
  },

  //   logout() {
  //     return http.post(URL_LOGOUT);
  //   },
};

export default authApi;
