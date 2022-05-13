import React, { Fragment } from "react";
import { SearchState } from "./Search";
import { Skeleton } from "@mui/material";
import { TreeView, TreeItem } from "@mui/lab";
import {
  ExpandMore as ExpandMoreIcon,
  ChevronRight as ChevronRightIcon,
} from "@mui/icons-material";
import set from "lodash/set";

import { SearchResponse } from "../../commons/types";
import { SearchResult } from "./SearchResult";
import { SearchError } from "./SearchError";
import styles from "./Search.module.scss";

type SearchResultsProps = {
  state: SearchState;
};

const convertResultsToTree = (results: SearchResponse, rootPath: string) => {
  const tree = {};
  Object.entries(results).forEach(([service, { phrases, results }]) =>
    results.forEach(({ filePath, matches }) => {
      const item = {
        phrases,
        filePath,
        service: service as keyof SearchResponse,
        matches,
      };

      set(tree, filePath.replace(rootPath, "").split("/").splice(1), item);
    })
  );
  return tree;
};

export const SearchResultsList: React.VFC<SearchResultsProps> = ({ state }) => {
  if (state.status === "SUCCESS" || state.status === "PARTIAL_SUCCESS") {
    const tree = convertResultsToTree(state.results, state.rootPath);

    const renderTree = (node) => (
      <Fragment>
        {Object.keys(node).map((key) =>
          !key.includes(".") ? (
            <TreeItem
              nodeId={key}
              label={key}
              classes={{
                content: styles.treeItemContent,
                label: styles.treeItemLabel,
              }}
            >
              {renderTree(node[key])}
            </TreeItem>
          ) : null
        )}
        {Object.keys(node).map((key) =>
          key.includes(".") ? <SearchResult {...node[key]} /> : null
        )}
      </Fragment>
    );

    return (
      <Fragment>
        {Object.entries(state.results).map(([service, { errors }]) => (
          <SearchError service={service} errors={errors} />
        ))}
        <TreeView
          defaultCollapseIcon={<ExpandMoreIcon />}
          defaultExpandIcon={<ChevronRightIcon />}
          defaultExpanded={[state.rootPath]}
          sx={{ my: 4 }}
        >
          <TreeItem
            nodeId={state.rootPath}
            label={state.rootPath}
            classes={{
              content: styles.treeItemContent,
              label: styles.treeItemLabel,
            }}
          >
            {renderTree(tree)}
            {state.status === "PARTIAL_SUCCESS" && (
              <Skeleton
                width="100%"
                height={60}
                sx={{ my: 2, transform: "none" }}
              />
            )}
          </TreeItem>
        </TreeView>
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
