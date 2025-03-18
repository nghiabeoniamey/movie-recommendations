import request from "./request";
import {PREFIX_API_CATEGORY_ADMIN, PREFIX_API_MOVIE_ADMIN, PREFIX_API_REVIEW_ADMIN} from "../utils/constants/url";
import {AxiosResponse} from "axios";
import {DefaultResponse} from "../utils/types/api.common";

export const AdminReviewApi = {

    GetReviews: async (params: any) => {
        const res = (await request({
            url: `${PREFIX_API_REVIEW_ADMIN}`,
            method: "GET",
            params: params,
        })) as AxiosResponse<
            DefaultResponse<Array<Object>>
        >;

        return res.data;
    },

    DeleteReview: async (id: any) => {
        return await request({
            url: `${PREFIX_API_CATEGORY_ADMIN}/${id}`,
            method: "DELETE"
        }) as AxiosResponse<
            DefaultResponse<Object>
        >;
    },

};
