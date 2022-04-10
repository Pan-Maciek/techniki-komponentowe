# pdf-search

Returns paths of .pdf files containing a given phrase.
For each file, additionally sends the paragraph containing the phrase,
as well as indices of occurrences in each paragraph.

Uses apache pdf-box for pdf parsing and akka-http for http server

### Available at

`http://localhost:9021`

### Request format

`http://localhost:9021/search?rootPath={path}&phrase={phrase}`

Example

`http://localhost:9021/search?rootPath=/app/files&phrase=sekwencji`

### Example response (success)

`curl --location --request GET 'http://localhost:9021/search?rootPath=app/files&phrase=sekwencji`

```json
{
  "pdf-search": {
    "errors": [],
    "phrase": "sekwencji",
    "results": [
      {
        "Matchess": [
          {
            "indices": [
              38
            ],
            "searchContext": "4. po każdym wprowadzeniu prawidłowej sekwencji będzie się ona wydłużać, aż do"
          },
          {
            "indices": [
              10
            ],
            "searchContext": "w temacie sekwencji przewodów"
          }
        ],
        "path": "/app/files/ktane-manual.pdf"
      }
    ],
    "status": "ok"
  }
}
```

### Example response (error)

`curl --location --request GET 'http://localhost:9021/search?rootPath=app/dir&phrase=sekwencji`

```json
{
  "errors": [
    "/app/dir"
  ],
  "phrase": "sekwencji",
  "results": [],
  "status": "error"
}
```
