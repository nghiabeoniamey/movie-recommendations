import {Navigate, Outlet} from "react-router-dom";
import {useAuthStore} from "../utils/stores/auth.js";
import * as React from "react";

const ProtectedRoute: React.FC = () => {
    const {isAuthenticated} = useAuthStore();

    if (!isAuthenticated) {
        return <Navigate to="/login" replace/>;
    }

    return <Outlet/>;
};

export default ProtectedRoute;