import React, { Fragment } from "react";
import { SearchState } from "./Search";
import { Skeleton } from "@mui/material";
import { SearchResponse } from "../../commons/types";
import { SearchResult } from "./SearchResult";
import { SearchError } from "./SearchError";

type SearchResultsProps = {
  state: SearchState;
};

export const SearchResultsList: React.VFC<SearchResultsProps> = ({ state }) => {
  if (state.status === "SUCCESS") {
    return (
      <Fragment>
        {Object.entries(state.results).map(([service, { errors }]) => (
          <SearchError service={service} errors={errors} />
        ))}
        {Object.entries(state.results).map(([service, { phrases, results }]) =>
          results.map((result) => (
            <SearchResult
              phrases={phrases}
              filePath={result.filePath}
              service={service as keyof SearchResponse}
              matches={result.matches}
            />
          ))
        )}
      </Fragment>
    );
  }

  if (state.status === "LOADING") {
    return (
      <Fragment>
        {[...Array(3)].map((value, index) => (
          <Skeleton
            key={index}
            width="100%"
            height={60}
            sx={{ my: 2, transform: "none" }}
          />
        ))}
      </Fragment>
    );
  }

  if (state.status === "ERROR") {
    return (
      <SearchError
        service="axios"
        // @ts-ignore
        errors={[JSON.stringify(state.error.toJSON(), null, "\t")]}
      />
    );
  }

  return <Fragment />;
};
