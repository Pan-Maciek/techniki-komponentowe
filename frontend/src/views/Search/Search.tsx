import React, { useState } from "react";
import { Container } from "@mui/material";
import { SubmitHandler } from "react-hook-form";
import { SearchParams, SearchResponse } from "../../commons/types";
import { search } from "../../api/searchApi";
import { SearchForm } from "./SearchForm";
import { SearchResultsList } from "./SearchResultsList";

export type SearchState =
  | {
      status: "IDLE" | "LOADING";
    }
  | {
      status: "PARTIAL_SUCCESS" | "SUCCESS";
      results: SearchResponse;
      rootPath: string;
    }
  | {
      status: "ERROR";
      error: unknown;
    };

export const Search = () => {
  const [searchState, setSearchState] = useState<SearchState>({
    status: "IDLE",
  });

  const onSubmit: SubmitHandler<SearchParams> = async (data) => {
    data.additionalInfo.enabledFormats =
      data.additionalInfo.enabledFormats.filter((format: string) => !!format);
    data.additionalInfo.lang = data.additionalInfo.lang.filter(
      (format: string) => !!format
    );

    setSearchState({ status: "LOADING" });
    try {
      const enabledFormats = data.additionalInfo.enabledFormats;

      enabledFormats.forEach((format) => {
        data.additionalInfo.enabledFormats = [format];
        search(data).then((results) => {
          setSearchState((state) => {
            const currentResults =
              state.status === "PARTIAL_SUCCESS" ? state.results : {};
            const newResults = { ...currentResults, ...results };

            return {
              status:
                Object.keys(newResults).length === enabledFormats.length
                  ? "SUCCESS"
                  : "PARTIAL_SUCCESS",
              results: newResults,
              rootPath: data.rootPath,
            };
          });
        });
      });
    } catch (error) {
      setSearchState({ status: "ERROR", error });
    }
  };

  console.log(searchState);

  return (
    <Container>
      <h2>Ultimate Search Engine</h2>
      <SearchForm onSubmit={onSubmit} />
      <SearchResultsList state={searchState} />
    </Container>
  );
};
