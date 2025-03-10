import axios from "axios";
import { createContext, useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import {
  clearLS,
  getAccessTokenFromLS,
  getProfileFromLS,
  setProfileToLS,
} from "../utils/storage";
import { useNavigate } from "react-router-dom";

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(
    Boolean(getAccessTokenFromLS())
  );
  const [userId, setUserId] = useState();
  const [role, setRole] = useState();

  useEffect(() => {
    const storedUser = getProfileFromLS();
    if (storedUser) {
      setUserId(storedUser.id);
      setRole(storedUser.role);
    }
  }, []);

  const login = (accessToken) => {
    const decoded = jwtDecode(accessToken);
    // console.log("Decoded token inside login:", decoded);
    const { id, roleName } = decoded;
    setUserId(id);
    setRole(roleName);
    setProfileToLS({ id, role: roleName });
  };

  const logout = () => {
    setUserId(null);
    setRole(null);
    setIsAuthenticated(false);
    clearLS();
    window.location.href = "/login";
  };

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        setIsAuthenticated,
        userId,
        login,
        logout,
        role,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };
