export type SearchParams = {
  phrase: string;
  rootPath: string;
  additionalInfo: {
    enabledFormats: string[];
    lang: string[];
  };
};

type ServiceResponse<Match> = {
  status: "ok" | "error";
  lang: string[];
  phrases: string[];
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
  "pdf-search": ServiceResponse<RichTextMatch>;
};

export const SERVICES: Array<keyof SearchResponse> = [
  "text-search",
  "odt-search",
  "pdf-search",
];

export const LANGUAGES = ["en", "de"] as const;
