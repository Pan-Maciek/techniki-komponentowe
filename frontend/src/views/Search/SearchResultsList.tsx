import React, { Fragment } from "react";
import { SearchState } from "./Search";
import { Box, Paper, Skeleton } from "@mui/material";
import styles from "./Search.module.scss";
import {
  TextSnippet as TextSnippetIcon,
  TextFormat as TextFormatIcon,
} from "@mui/icons-material";
import Highlighter from "react-highlight-words";
import { SearchResponse, Match, PlainTextMatch } from "../../commons/types";

type SearchResultsProps = {
  state: SearchState;
};

const typeIconMap = {
  "text-search": <TextSnippetIcon />,
  "odt-search": <TextFormatIcon />,
};

const SearchResult: React.VFC<{
  phrase: string;
  filePath: string;
  service: keyof SearchResponse;
  matches: Array<Match>;
}> = ({ phrase, filePath, service, matches }) => (
  <Paper
    key={filePath}
    sx={{
      my: 2,
      p: 2,
    }}
  >
    <Box
      sx={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        mb: 2,
      }}
    >
      <div className={styles.resultPath}>{filePath}</div>
      {typeIconMap[service]}
    </Box>

    {matches.map((match) => (
      <Box
        sx={{
          ml: 2,
          my: 1,
        }}
      >
        {service === "text-search" &&
          `[${(match as PlainTextMatch).lineNumber}]`}{" "}
        <Highlighter
          searchWords={[phrase]}
          textToHighlight={match.searchContext}
        />
      </Box>
    ))}
  </Paper>
);

export const SearchResultsList: React.VFC<SearchResultsProps> = ({ state }) => {
  if (state.status === "SUCCESS") {
    return (
      <Fragment>
        {Object.entries(state.results).map(([service, response]) => {
          return response.results.map((result) => (
            <SearchResult
              phrase={response.phrase}
              filePath={result.filePath}
              service={service as keyof SearchResponse}
              matches={result.matches}
            />
          ));
        })}
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
    // @ts-ignore
    return <div>Error occurred: {JSON.stringify(state.error.toJSON())}</div>;
  }

  return <Fragment />;
};
