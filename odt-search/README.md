# odt-search

Returns paths of .odt and .odf files containing a given phrase.
For each file, additionally sends the paragraph containing the phrase,
as well as indices of occurrences in each paragraph.

### Available at

`http://localhost:9011`

### Request format

`http://localhost:9011/search?rootPath={path}&phrase={phrase}`

Example

`http://localhost:9011/search?rootPath=/app/files&phrase=brief`

### Example response (success)

```json
{
  "phrase": "brief",
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
        }
      ]
    },
    {
      "filePath": "/app/files/a/bells.odt",
      "matches": [
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
  "phrase": "anything",
  "status": "error",
  "results": [],
  "errors": [
    "/app/dir"
  ]
}
```


