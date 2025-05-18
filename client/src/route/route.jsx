import {createBrowserRouter} from "react-router-dom";
import Layout from "./Layout";
import HomePage from "../page/HomePage";
import MoviesDetail from "../page/MovieDetail";
import SearchPage from "../page/SearchPage";
import AdminLayout from "../dashboard/AdminLayout";
import Dashboard from "../dashboard/page/Dash";
import VerifyRedirect from "../page/auth/VerifyRedirect";
import Login from "../page/auth/Login";
import SignUp from "../page/auth/SignUp";
import UserManager from "../dashboard/page/UserManager";
import CategoryManager from "../dashboard/page/CategoryManager";
import Profile from "../page/Profile";
import MovieManager from "../dashboard/page/MovieManager";
import Movies from "../page/AllMovie";
import ReviewManager from "../dashboard/page/ReviewManager";
import MovieDetailDemo from "../page/MovieDetailDemo";
import SearchResults from "../page/SearchResults";
import Redirect from "./Redirect/Redirect.tsx";
import {ROUTES_CONSTANTS} from "../utils/constants/path.ts";
import ProtectedRoute from "./ProtectedRoute.tsx";
import ReviewsPage from "../page/ReviewsPage.jsx";
import WatchHistoryPage from "../page/WatchHistoryPage.jsx";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout/>,
        children: [
            {
                path: ROUTES_CONSTANTS.CLIENT.path,
                element: <HomePage/>,
            },
            {
                path: ROUTES_CONSTANTS.REDIRECT.path,
                element: <Redirect/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.MOVIES.path,
                element: <Movies/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.MOVIES_DETAIL.path,
                element: <MoviesDetail/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.MOVIES_DETAIL_DEMO.path,
                element: <MovieDetailDemo/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.SEARCH_RESULT.path,
                element: <SearchResults/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.SEARCH_PAGE.path,
                element: <SearchPage/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.REVIEWS.path,
                element: <ReviewsPage/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.WATCH_HISTORY.path,
                element: <WatchHistoryPage/>,
            },
            {
                path: ROUTES_CONSTANTS.CLIENT.children.PROFILE.path,
                element: <ProtectedRoute/>,
                children: [
                    {
                        index: true,
                        element: <Profile/>,
                    },
                ],
            },
        ],
    },
    {
        path: ROUTES_CONSTANTS.AUTHENTICATION.children.LOGIN.path,
        element: <Login/>,
    },
    {
        path: "/auth/redirect/verify",
        element: <VerifyRedirect/>,
    },
    {
        path: ROUTES_CONSTANTS.AUTHENTICATION.children.REGISTER.path,
        element: <SignUp/>,
    },
    {
        path: ROUTES_CONSTANTS.ADMIN.path,
        element: <AdminLayout/>, // Layout riêng cho admin
        children: [
            {
                path: ROUTES_CONSTANTS.ADMIN.children.DASHBOARD.path,
                element: <Dashboard/>,
            },
            {
                path: ROUTES_CONSTANTS.ADMIN.children.MOVIES.path,
                element: <MovieManager/>,
            },
            {
                path: ROUTES_CONSTANTS.ADMIN.children.CATEGORY.path,
                element: <CategoryManager/>,
            },
            {
                path: ROUTES_CONSTANTS.ADMIN.children.USERS.path,
                element: <UserManager/>,
            },
            {
                path: ROUTES_CONSTANTS.ADMIN.children.REVIEWS.path,
                element: <ReviewManager/>,
            },
            {
                index: true,
                element: <Dashboard/>,
            },
        ],
    },
]);
