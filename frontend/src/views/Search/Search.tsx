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
      status: "SUCCESS";
      results: SearchResponse;
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
      data.additionalInfo.enabledFormats.filter((format) => !!format);

    console.log(data);

    setSearchState({ status: "LOADING" });
    try {
      const results = await search(data);
      setSearchState({ status: "SUCCESS", results });
    } catch (error) {
      setSearchState({ status: "ERROR", error });
    }
  };

  return (
    <Container>
      <h2>Ultimate Search Engine</h2>
      <SearchForm onSubmit={onSubmit} />
      <SearchResultsList state={searchState} />
    </Container>
  );
};
