# pdf-search

Returns paths of .pdf files containing a given phrase.
For each file, additionally sends the paragraph containing the phrase,
as well as indices of occurrences in each paragraph.

Uses apache pdf-box for pdf parsing and akka-http for http server

### Available at

`http://localhost:9021`

### Request format

`http://localhost:9021/search?rootPath={path}&phrases={phrase1,phrase2}&lang={lang1,lang2}`

Example

`http://localhost:9021/search?rootPath=/app/files&phrases=zidentyfikowane,sekwencji&lang=pl,eng`

### Example response (success)

`curl --location --request GET 'http://localhost:9021/search?rootPath=/app/files&phrases=zidentyfikowane,sekwencji&lang=pl,eng`

```json
{
  "errors": [],
  "lang": [
    "pl",
    "eng"
  ],
  "phrases": [
    "zidentyfikowane",
    "sekwencji"
  ],
  "results": [
    {
      "filePath": "/app/files/ktane-manual.pdf",
      "matches": [
        {
          "indices": [
            16
          ],
          "searchContext": "moduły mogą być zidentyfikowane dzięki diodzie led w prawym"
        },
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
      ]
    }
  ],
  "status": "ok"
}
```

### Example response (error)

`curl --location --request GET 'http://localhost:9021/search?rootPath=app/dir&phrase=sekwencji`

```json
{
  "errors": [
    "/app/dir"
  ],
  "lang": [
    "pl",
    "eng"
  ],
  "phrases": [
    "zidentyfikowane",
    "sekwencji"
  ],
  "results": [],
  "status": "error"
}
```
