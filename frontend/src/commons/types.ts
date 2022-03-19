export type SearchParams = {
  phrase: string;
  rootPath: string;
};

export type SearchResults = Array<{
  path: string;
}>;
