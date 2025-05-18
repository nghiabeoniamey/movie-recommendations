import {PREFIX_API_CONNECTION_PYTHON, PREFIX_API_MOVIE_USER} from "../utils/constants/url";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common";
import request from "./request";

export const UserMovieApi = {

    GetMovies: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_MOVIE_USER}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    GetMovieById: async (id: any) => {
        const res = (await request({
            url: `${PREFIX_API_MOVIE_USER}/${id}`,
            method: "GET",
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },

    GetMovieRecommendations: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_CONNECTION_PYTHON}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    SearchMovie: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_MOVIE_USER}/search`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    ViewMovie: async (req: any) => {
        const data = {
            movieId: req.id,
        }

        const res = (await request({
            url: `${PREFIX_API_MOVIE_USER}/view`,
            method: "POST",
            data: data,
        })) as AxiosResponse<DefaultResponse<Object>>;

        return res.data;
    },

    GetMovieReviews: async () => {
        const res = (await request({
            url: `${PREFIX_API_CONNECTION_PYTHON}/reviewer`,
            method: "GET"
        })) as AxiosResponse<DefaultResponse<Object>>;

        return res.data;
    },

    GetMovieWatchHistory: async () => {
        const res = (await request({
            url: `${PREFIX_API_CONNECTION_PYTHON}/view`,
            method: "GET"
        })) as AxiosResponse<DefaultResponse<Object>>;

        return res.data;
    },

    CreateMovie: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_MOVIE_USER}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },

    UpdateMovie: async (id: any, data: any) => {
        return await request({
            url: `${PREFIX_API_MOVIE_USER}/${id}`,
            method: "PUT",
            data: data
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

    DeleteMovie: async (id: any) => {
        return await request({
            url: `${PREFIX_API_MOVIE_USER}/${id}`,
            method: "DELETE"
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

};
