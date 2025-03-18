import {PREFIX_API_MOVIE_ADMIN} from "../utils/constants/url";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common";
import request from "./request";

export const AdminMovieApi = {

    GetMovies: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_MOVIE_ADMIN}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    CreateMovie: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_MOVIE_ADMIN}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },

    UpdateMovie: async (id: any, data: any) => {
        return await request({
            url: `${PREFIX_API_MOVIE_ADMIN}/${id}`,
            method: "PUT",
            data: data
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

    DeleteMovie: async (id: any) => {
        return await request({
            url: `${PREFIX_API_MOVIE_ADMIN}/${id}`,
            method: "DELETE"
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

};
