export const ROUTES_CONSTANTS = {
    AUTHENTICATION: {
        path: "/",
        name: "T-Shirt Two",
        children: {
            LOGIN: {
                path: "login",
                name: "login",
            },
            REGISTER: {
                path: "signup",
                name: "signup",
            },
            FORGOT_PASSWORD: {
                path: "forgot-password",
                name: "forgot-password",
            },
            VERIFY: {
                path: "auth/redirect/verify",
                name: "verify",
            }
        }
    },
    ADMIN: {
        path: "/admin",
        name: "admin",
        children: {
            DASHBOARD: {
                path: "dashboard",
                name: "dashboard-management",
            },
            MOVIES: {
                path: "movies",
                name: "movies-management",
            },
            CATEGORY: {
                path: "category",
                name: "category-management",
            },
            USERS: {
                path: "users",
                name: "users-management",
            },
            REVIEWS: {
                path: "reviews",
                name: "reviews-management",
            },

        },
    },
    NOT_FOUND: {
        path: "/:pathMatch(.*)*",
        name: "NotFound",
    },
    REDIRECT: {
        path: "/redirect",
        name: "redirect",
    },
    CLIENT: {
        path: "/",
        name: "client",
        children: {
            MOVIES: {
                path: "movies",
                name: "client-movies"
            },
            MOVIES_DETAIL: {
                path: "movies/:id",
                name: "client-movies-detail",
            },
            MOVIES_DETAIL_DEMO: {
                path: "detail/:id",
                name: "client-movies-detail-demo",
            },
            SEARCH_RESULT: {
                path: "search",
                name: "client-movies-search-results",
            },
            SEARCH_PAGE: {
                path: "find",
                name: "client-movies-detail-demo",
            },
            PROFILE: {
                path: "profile",
                name: "client-profile",
            },
            REVIEWS: {
                path: "reviews",
                name: "client-reviews",
            },
            WATCH_HISTORY: {
                path: "watch-history",
                name: "client-watch-history",
            }
        },
    },
};
