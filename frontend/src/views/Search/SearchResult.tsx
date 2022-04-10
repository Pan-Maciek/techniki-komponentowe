import React from "react";
import { Match, PlainTextMatch, SearchResponse } from "../../commons/types";
import { Box, Paper } from "@mui/material";
import styles from "./Search.module.scss";
import Highlighter from "react-highlight-words";
import {
  TextFormat as TextFormatIcon,
  TextSnippet as TextSnippetIcon,
} from "@mui/icons-material";

const serviceIconMap: Record<keyof SearchResponse, JSX.Element> = {
  "text-search": <TextSnippetIcon />,
  "odt-search": <TextFormatIcon />,
};

export const SearchResult: React.VFC<{
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
      {serviceIconMap[service]}
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
