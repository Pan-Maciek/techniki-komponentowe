import React, { Fragment } from "react";
import { SearchState } from "./Search";
import { Paper, Skeleton } from "@mui/material";
import styles from "./Search.module.scss";
import { TextSnippet as TextSnippetIcon } from "@mui/icons-material";

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
            key={result.path}
            sx={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              my: 2,
              p: 2,
            }}
          >
            <div className={styles.resultPath}>{result.path}</div>
            {typeIconMap.plainText}
          </Paper>
        ))}
      </Fragment>
    );
  }

  if (state.status === "LOADING") {
    return (
      <Fragment>
        {[...Array(3)].map(() => (
          <Skeleton
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
