import React, { useState } from "react";
import { Container } from "@mui/material";
import { SubmitHandler } from "react-hook-form";
import { SearchParams, SearchResults } from "../../commons/types";
import { search } from "../../api/searchApi";
import { SearchForm } from "./SearchForm";
import { SearchResultsList } from "./SearchResultsList";

export type SearchState =
  | {
      status: "IDLE" | "LOADING" | "ERROR";
    }
  | {
      status: "SUCCESS";
      results: SearchResults;
      phrase: string;
    };

export const Search = () => {
  const [searchState, setSearchState] = useState<SearchState>({
    status: "IDLE",
  });

  const onSubmit: SubmitHandler<SearchParams> = async (data) => {
    setSearchState({ status: "LOADING" });
    try {
      const results = await search(data);
      setSearchState({ status: "SUCCESS", results, phrase: data.phrase });
    } catch (e) {
      setSearchState({ status: "ERROR" });
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
