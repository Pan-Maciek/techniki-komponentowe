import axios from "axios";
import { SearchParams, SearchResponse } from "../commons/types";
import qs from "qs";

const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
});

export const search = async (params: SearchParams): Promise<SearchResponse> => {
  const response = await axiosInstance.get<SearchResponse>("/search", {
    params,
    paramsSerializer: (params) =>
      qs.stringify(params, { allowDots: true, arrayFormat: "comma" }),
  });
  return response.data;
};
