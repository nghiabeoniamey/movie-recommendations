import { useEffect } from "react";
import Banner from "./components/Banner";
import Header from "./components/Header";
import MovieList from "./components/MovieList";
import { useState } from "react";
import MovieSearch from "./components/MovieSearch";

import { RouterProvider } from "react-router-dom";
import { router } from "./route/route";
import { AuthProvider } from "./context/AuthContext";
import { ToastContainer, toast } from "react-toastify";

function App() {
  return (
    <AuthProvider>
      <RouterProvider router={router} />;
      <ToastContainer />
    </AuthProvider>
  );
}

export default App;
