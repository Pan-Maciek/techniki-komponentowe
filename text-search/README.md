# odt-search

Returns paths of .md and .txt files containing a given phrase.
For each file, additionally sends the line containing the phrase,
index of the line, as well as indices of occurrences in each line.

### Available at

`http://localhost:9001`

### Request format

`http://localhost:9001/search?rootPath={path}&phrases={phrase1,phrase2}&lang={lang1,lang2}`

Example

`http://localhost:9001/search?rootPath=/app&phrases=words,is&lang=eng,pl`

### Example response (success)

```json
{
  "phrases": [ "words", "hord" ],
  "lang": [ "eng", "pl" ],
  "status": "ok",
  "results": [
    {
      "filePath": "/app/files/sample.md",
      "matches": [
        {
          "searchContext": "For what words cannot express",
          "indices": [ 9 ],
          "lineNumber": 3
        },
        {
          "searchContext": "Hail to the hordes",
          "indices": [ 12 ],
          "lineNumber": 14
        }
      ]
    }
  ],
  "errors": []
}
```

### Example response (error)

```json
{
  "phrases": [ "words", "hord" ],
  "lang": [ "eng", "pl" ],
  "status": "error",
  "results": [],
  "errors": [
    "Could not find a part of the path '/app/dir'."
  ]
}
```


