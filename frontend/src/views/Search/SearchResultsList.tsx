import React, { Fragment } from "react";
import { SearchState } from "./Search";
import { Box, Paper, Skeleton } from "@mui/material";
import styles from "./Search.module.scss";
import { TextSnippet as TextSnippetIcon } from "@mui/icons-material";
import Highlighter from "react-highlight-words";

type SearchResultsProps = {
  state: SearchState;
};

const typeIconMap = {
  plainText: <TextSnippetIcon />,
};

export const SearchResultsList: React.VFC<SearchResultsProps> = ({ state }) => {
  if (state.status === "SUCCESS") {
    return (
      <Fragment>
        {state.results.map((result) => (
          <Paper
            key={result.filePath}
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
              <div className={styles.resultPath}>{result.filePath}</div>
              {typeIconMap.plainText}
            </Box>

            {result.results.map((line) => (
              <Box
                sx={{
                  ml: 2,
                  my: 1,
                }}
              >
                [{line.lineNumber}]{" "}
                <Highlighter
                  searchWords={[state.phrase]}
                  textToHighlight={line.searchContext}
                />
              </Box>
            ))}
          </Paper>
        ))}
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
    return <div>Error occurred</div>;
  }

  return <Fragment />;
};
