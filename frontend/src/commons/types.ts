export type SearchParams = {
  phrase: string;
  rootPath: string;
};

export type SearchResults = Array<{
  filePath: string;
  results: Array<{
    searchContext: string;
    indices: Array<number>;
    lineNumber: number;
  }>;
}>;
