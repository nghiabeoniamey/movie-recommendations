import request from "./request";
import {PREFIX_API_REVIEW_USER} from "../utils/constants/url";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common";

export const UserReviewApi = {

    getReviews: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_REVIEW_USER}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },


    CreateReview: async (data: any) => {
        const res = (await request({
            url: `${PREFIX_API_REVIEW_USER}`,
            method: "POST",
            data: data,
        })) as AxiosResponse<
            DefaultResponse<Object>
        >;

        return res.data;
    },


    DeleteReview: async (id: any) => {
        return await request({
            url: `${PREFIX_API_REVIEW_USER}/${id}`,
            method: "DELETE"
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

};
