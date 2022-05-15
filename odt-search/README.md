# odt-search

Returns paths of .odt and .odf files containing a given phrase.
For each file, additionally sends the paragraph containing the phrase,
as well as indices of occurrences in each paragraph.

### Available at

`http://localhost:9011`

### Request format

`http://localhost:9011/search?rootPath={path}&phrases={phrase1,phrase2}&lang={lang1,lang2}`

Example

`http://localhost:9011/search?rootPath=/app/files&phrases=brief,hearts&lang=eng,pl`

### Example response (success)

```json
{
  "phrases": [
    "brief",
    "hearts"
  ],
  "lang": [
    "eng",
    "pl"
  ],
  "status": "ok",
  "results": [
    {
      "filePath": "/app/files/bells.odt",
      "matches": [
        {
          "searchContext": "Brief were the moments of bliss and gladness",
          "indices": [
            0
          ]
        },
        {
          "searchContext": "Here the nightly frost will creep inside the hearts",
          "indices": [
            45
          ]
        }
      ]
    },
    {
      "filePath": "/app/files/subfolder/bells.odt",
      "matches": [
        {
          "searchContext": "Here the nightly frost will creep inside the hearts",
          "indices": [
            45
          ]
        },
        {
          "searchContext": "Brief were the moments of bliss and gladness",
          "indices": [
            0
          ]
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
  "phrases": [
    "brief",
    "hearts"
  ],
  "lang": [
    "eng",
    "pl"
  ],
  "status": "error",
  "results": [],
  "errors": [
    "/app/dir"
  ]
}
```


