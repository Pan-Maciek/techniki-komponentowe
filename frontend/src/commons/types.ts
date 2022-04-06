export type SearchParams = {
  phrase: string;
  rootPath: string;
};

type ServiceResponse<Match> = {
  status: "ok" | "error";
  phrase: string;
  results: Array<{
    filePath: string;
    matches: Array<Match>;
  }>;
  errors: Array<string>;
};

export type PlainTextMatch = {
  indices: Array<number>;
  searchContext: string;
  lineNumber: number;
};

export type RichTextMatch = {
  indices: Array<number>;
  searchContext: string;
};

export type Match = PlainTextMatch | RichTextMatch;

export type SearchResponse = {
  "text-search": ServiceResponse<PlainTextMatch>;
  "odt-search": ServiceResponse<RichTextMatch>;
};
