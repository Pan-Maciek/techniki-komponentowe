import axios from "axios";
import { SearchParams, SearchResults } from "../commons/types";

const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
});

export const search = async (params: SearchParams) => {
  const response = await axiosInstance.get("/search", {
    params,
  });
  return response.data[0] as SearchResults;
};
