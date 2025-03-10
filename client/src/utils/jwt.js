import { jwtDecode } from "jwt-decode";
export const decoded = jwtDecode(token, { header: true });
