import axios from "axios";
import { SearchParams, SearchResults } from "../commons/types";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/",
});

export const search = async (params: SearchParams) => {
  const response = await axiosInstance.get("/search", {
    params,
  });
  return response.data[0] as SearchResults;
};
