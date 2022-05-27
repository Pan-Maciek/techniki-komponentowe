# words-forms

Return forms from a given phrase. Available forms:

-synonyms

-typos

-forms

User should determine which forms are preferred.

### Available at

`http://localhost:8187`

### Request format

Synonyms:

`http://localhost:8187/synonyms/search?phrases={phrases}`

Typos:

`http://localhost:8187/typos/search?phrases={phrases}`

Forms

`http://localhost:8187/forms/search?phrases={phrases}`

Example (synonyms)

`http://localhost:8187/synonyms/search?phrases=dzwon`

### Example response


```json
[
  "kolizja",
  "zderzenie",
  "wypadek",
  "karambol",
  "kraksa"
]
```

Example (typos)

`http://localhost:8187/typos/search?phrases=dzwon`

### Example response


```json
[
  "ezwon",
  "dawon",
  "dzqon",
  "dzwin",
  "dzwob"
]
```

Example (forms)

`http://localhost:8187/forms/search?phrases=dzwon`

### Example response


```json
[
  "dzwona",
  "dzwonowi",
  "dzwonem",
  "dzwonie",
  "dzwony"
]
```


