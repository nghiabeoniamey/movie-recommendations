// src/stores/authentication.ts
import {create} from "zustand";
import {persist} from "zustand/middleware";
import {localStorageAction} from "../storage";
import {ACCESS_TOKEN_STORAGE_KEY, REFRESH_TOKEN_STORAGE_KEY, USER_INFO_STORAGE_KEY} from "../constants/storageKey";
import {UserInformation} from "../types/auth.type";

type AuthenticationData = {
    user: UserInformation;
    accessToken: string;
    refreshToken: string;
};

type AuthStore = {
    user: UserInformation | null;
    accessToken: string | null;
    refreshToken: string | null;
    isAuthenticated: boolean;
    login: (tokenData: AuthenticationData) => void;
    logout: () => void;
};

export const useAuthStore = create<AuthStore>()(
    persist(
        (set) => ({
            user: localStorageAction.get(USER_INFO_STORAGE_KEY) || null,
            accessToken: localStorageAction.get(ACCESS_TOKEN_STORAGE_KEY) || null,
            refreshToken: localStorageAction.get(REFRESH_TOKEN_STORAGE_KEY) || null,
            isAuthenticated: localStorageAction.get(ACCESS_TOKEN_STORAGE_KEY) !== null,

            login: (tokenData: AuthenticationData) => {
                const userData = tokenData.user;
                set({
                    user: userData,
                    accessToken: tokenData.accessToken,
                    refreshToken: tokenData.refreshToken,
                    isAuthenticated: true,
                });

                localStorageAction.set(USER_INFO_STORAGE_KEY, userData);
                localStorageAction.set(ACCESS_TOKEN_STORAGE_KEY, tokenData.accessToken);
                localStorageAction.set(REFRESH_TOKEN_STORAGE_KEY, tokenData.refreshToken);

                console.log("🤡 Current User Info:", userData);
            },

            logout: () => {
                set({
                    user: null,
                    accessToken: null,
                    refreshToken: null,
                    isAuthenticated: false,
                });

                localStorageAction.remove(USER_INFO_STORAGE_KEY);
                localStorageAction.remove(ACCESS_TOKEN_STORAGE_KEY);
                localStorageAction.remove(REFRESH_TOKEN_STORAGE_KEY);
            },
        }),
        {
            name: "auth-storage", // Tên key để lưu trữ trong localStorage
        }
    )
);