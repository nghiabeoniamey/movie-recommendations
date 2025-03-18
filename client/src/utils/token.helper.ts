import { jwtDecode } from "jwt-decode";
import {DecodedToken, UserInformation} from "./types/auth.type";

export const getUserInformation = (token: string): UserInformation => {
  const decoded = jwtDecode<DecodedToken>(token);
  return {
    id: decoded.id,
    userName: decoded.userName,
    email: decoded.email,
    subscriptionType: decoded.subscriptionType,
    profilePicture: decoded.profilePicture,
    roleCode: decoded.roleCode,
    roleName: decoded.roleName,
  };
};

export const getRolesUser = (token: string): string => {
  const decoded = jwtDecode<DecodedToken>(token);
  return decoded.roleCode;
};

export const getExpireTime = (token: string): number => {
  const decoded = jwtDecode<DecodedToken>(token);
  return decoded.exp;
};

export const isTokenExpired = (token: string): boolean => {
  const expireTime = getExpireTime(token);
  return Date.now() >= expireTime * 1000;
};
