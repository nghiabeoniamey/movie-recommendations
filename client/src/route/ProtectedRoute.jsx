import { useContext } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

const ProtectedRoute = () => {
  const { userId, role } = useContext(AuthContext);

  if (!userId) {
    return <Navigate to="/login" />;
  }

  //   if (role && role !== roleApp) {
  //     return <Navigate to="/" replace />;
  //   }

  return <Outlet />;
};

export default ProtectedRoute;
