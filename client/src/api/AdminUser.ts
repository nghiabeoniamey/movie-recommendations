import request from "./request.js";
import {PREFIX_API_USER_ADMIN} from "../utils/constants/url.js";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common.js";

export const AdminUserApi = {

    GetUsers: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_USER_ADMIN}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    CreateUser: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_USER_ADMIN}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },

    GetUserById: async (id: any) => {
        const res = (await request({
            url: `${PREFIX_API_USER_ADMIN}/${id}`,
            method: "GET",
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },

    UpdateUser: async (id: any, data: any) => {
        return await request({
            url: `${PREFIX_API_USER_ADMIN}/${id}`,
            method: "PUT",
            data: data
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

    CheckActive: async (id: any) => {
        return await request({
            url: `${PREFIX_API_USER_ADMIN}/active/${id}`,
            method: "GET"
        }) as AxiosResponse<DefaultResponse<Object>>;
    },

    DeleteUser: async (id: any) => {
        return await request({
            url: `${PREFIX_API_USER_ADMIN}/${id}`,
            method: "DELETE"
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },
};
