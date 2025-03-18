// API URL
export const API_URL = `http://localhost:6868/api/v1` as string;

// DOMAIN
export const DOMAIN_BACKEND = `http://localhost:6868` as string;

export const DOMAIN_FRONTEND = `http://localhost:8888` as string;

export const URL_OAUTH2_GOOGLE = `${DOMAIN_BACKEND}/oauth2/authorize/google?redirect_uri=` as string;
export const URL_OAUTH2_GITHUB = `${DOMAIN_BACKEND}/oauth2/authorize/github?redirect_uri=` as string;
export const URL_OAUTH2_FACEBOOK = `${DOMAIN_BACKEND}/oauth2/authorize/facebook?redirect_uri=` as string;

export const URL_FRONTEND = `${DOMAIN_FRONTEND}/redirect`;

// COMMON API
export const PREFIX_API_COMMON = `${API_URL}/common` as string;

// AUTH API
export const PREFIX_API_AUTH = `${API_URL}/auth` as string;
export const PREFIX_API_LOGIN = PREFIX_API_AUTH + `/login` as string;
export const PREFIX_API_LOGOUT = PREFIX_API_AUTH + `/logout` as string;
export const PREFIX_API_REGISTER = PREFIX_API_AUTH + `/register` as string;
export const PREFIX_API_FORGOT_PASSWORD = PREFIX_API_AUTH + `/forgot-password` as string;
export const PREFIX_API_REFRESH = PREFIX_API_AUTH + `/refresh` as string;


// ADMIN API
export const PREFIX_API_FEATURE_ADMIN = `${API_URL}/admin/feature` as string;
export const PREFIX_API_CATEGORY_ADMIN = `${API_URL}/admin/category` as string;
export const PREFIX_API_MOVIE_ADMIN = `${API_URL}/admin/movie` as string;
export const PREFIX_API_REVIEW_ADMIN = `${API_URL}/admin/review` as string;
export const PREFIX_API_USER_ADMIN = `${API_URL}/admin/user` as string;

// USER API
export const PREFIX_API_FEATURE_USER = `${API_URL}/user/feature` as string;
export const PREFIX_API_REVIEW_USER = `${API_URL}/user/review` as string;
export const PREFIX_API_MOVIE_USER = `${API_URL}/user/movie` as string;
export const PREFIX_API_CONNECTION_PYTHON = `${API_URL}/connection/python` as string;
