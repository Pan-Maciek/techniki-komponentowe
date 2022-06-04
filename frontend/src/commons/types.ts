export type SearchParams = {
  phrase: string;
  rootPath: string;
  additionalInfo: {
    enabledFormats: string[];
    lang: string[];
    forms: string[];
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

export type AudioMatch = {
  indices: Array<number>;
  searchContext: string;
};

export type ImageMatch = {
  indices: Array<number>;
  searchContext: string;
};

export type ArchiveMatch = {
  indices: Array<number>;
  searchContext: string;
};

export type Match =
  | PlainTextMatch
  | RichTextMatch
  | AudioMatch
  | ImageMatch
  | ArchiveMatch;

export type SearchResponse = {
  "text-search": ServiceResponse<PlainTextMatch>;
  "odt-search": ServiceResponse<RichTextMatch>;
  "pdf-search": ServiceResponse<RichTextMatch>;
  "microsoft-search": ServiceResponse<RichTextMatch>;
  "audio-search": ServiceResponse<AudioMatch>;
  "video-search": ServiceResponse<AudioMatch>;
  "video-search-ocr": ServiceResponse<ImageMatch>;
  "ocr-search": ServiceResponse<ImageMatch>;
  "archive-search": ServiceResponse<ArchiveMatch>;
};

export const SERVICES: Array<keyof SearchResponse> = [
  "text-search",
  "odt-search",
  "pdf-search",
  "microsoft-search",
  "audio-search",
  "video-search",
  "video-search-ocr",
  "ocr-search",
  "archive-search",
];

export const LANGUAGES = ["en", "de"] as const;

export const FORMS = ["synonyms", "forms", "typos"] as const;
