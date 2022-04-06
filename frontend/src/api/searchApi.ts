import axios from "axios";
import { SearchParams, SearchResponse } from "../commons/types";

const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
});

export const search = async (params: SearchParams): Promise<SearchResponse> => {
  const response = await axiosInstance.get<SearchResponse>("/search", {
    params,
  });
  return response.data;
};
