import {PREFIX_API_CATEGORY_ADMIN} from "../utils/constants/url";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common";
import request from "./request";

export const AdminCategoryApi = {

    GetCategories: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_CATEGORY_ADMIN}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    CreateCategory: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_CATEGORY_ADMIN}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },

    UpdateCategory: async (id: any, data: any) => {
        return await request({
            url: `${PREFIX_API_CATEGORY_ADMIN}/${id}`,
            method: "PUT",
            data: data
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

    DeleteCategory: async (id: any) => {
        return await request({
            url: `${PREFIX_API_CATEGORY_ADMIN}/${id}`,
            method: "DELETE"
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

};
