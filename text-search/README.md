# odt-search

Returns paths of .md and .txt files containing a given phrase.
For each file, additionally sends the line containing the phrase,
index of the line, as well as indices of occurrences in each line.

### Available at

`http://localhost:9001`

### Request format

`http://localhost:9001/search?rootPath={path}&phrases={phrase1,phrase2}`

Example

`http://localhost:9001/search?rootPath=/app/files&phrases=bond`

### Example response (success)

```json
{
  "phrases": ["bond"],
  "status": "ok",
  "results": [
    {
      "filePath": "/app/files/sample.md",
      "matches": [
        {
          "searchContext": "Will form a bond, impeccable art",
          "indices": [
            12
          ],
          "lineNumber": 6
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
  "phrases": ["bond"],
  "status": "error",
  "results": [],
  "errors": [
    "Could not find a part of the path '/app/dir'."
  ]
}
```


