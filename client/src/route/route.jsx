import { createBrowserRouter } from "react-router-dom";
import Layout from "./Layout";
import HomePage from "../page/HomePage";
import MovieDetail from "../page/MovieDetail";
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
import AllMovie from "../page/AllMovie";
import ProtectedRoute from "./ProtectedRoute";
import ReviewManager from "../dashboard/page/ReviewManager";
import MovieDetailDemo from "../page/MovieDetailDemo";
import SearchResults from "../page/SearchResults";
import { TestReview } from "../components/TestReview";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        path: "/",
        // element: <ProtectedRoute element={<Home />}></ProtectedRoute>,
        element: <HomePage />,
      },
      {
        path: "/all",
        element: <AllMovie />,
      },
      {
        path: "/movie/:id",
        element: <MovieDetail />,
      },
      {
        path: "/detail/:id",
        element: <MovieDetailDemo />,
      },
      {
        path: "/search",
        element: <SearchResults />,
      },
      {
        path: "/find",
        element: <SearchPage />,
      },
      {
        path: "/profile",
        element: <ProtectedRoute />,
        children: [
          {
            index: true,
            element: <Profile />,
          },
        ],
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/auth/redirect/verify",
    element: <VerifyRedirect />,
  },
  {
    path: "/signup",
    element: <SignUp />,
  },
  {
    path: "/admin",
    element: <AdminLayout />, // Layout riêng cho admin
    children: [
      {
        path: "dashboard",
        element: <Dashboard />,
      },
      {
        path: "movies",
        element: <MovieManager />,
      },
      {
        path: "category",
        element: <CategoryManager />,
      },
      {
        path: "users",
        element: <UserManager />,
      },
      {
        path: "reviews",
        element: <ReviewManager />,
      },
      {
        index: true,
        element: <Dashboard />,
      },
    ],
  },
  // {
  //   path: "/admin",
  //   element: <ProtectedRoute />,
  //   children: [
  //     {
  //       path: "/admin",
  //       element: <AdminLayout />,
  //       children: [
  //         {
  //           index: true,
  //           element: <Dashboard />,
  //         },
  //         {
  //           path: "dashboard",
  //           element: <Dashboard />,
  //         },
  //         {
  //           path: "settings",
  //           element: <Settings />,
  //         },
  //         {
  //           path: "tables",
  //           element: <Tables />,
  //         },
  //         {
  //           path: "movies",
  //           element: <MovieManager />,
  //         },
  //         {
  //           path: "category",
  //           element: <CategoryManager />,
  //         },
  //         {
  //           path: "users",
  //           element: <UserManager />,
  //         },
  //       ],
  //     },
  //   ],
  // },
]);
