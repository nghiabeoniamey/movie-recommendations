import {PREFIX_API_LOGIN, PREFIX_API_REGISTER} from "../utils/constants/url";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common";
import request from "./request";

const AuthApi = {

    registerAccount: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_REGISTER}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<DefaultResponse<Object>>;

        return res.data;
    },

    login: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_LOGIN}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<DefaultResponse<Object>>;

        return res.data;
    },

    verify: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_LOGIN}`,
            method: "PUT",
            params: params,
        })) as AxiosResponse<DefaultResponse<Object>>;

        return res.data;
    },
};

export default AuthApi;
