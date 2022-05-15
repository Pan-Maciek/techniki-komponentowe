# pdf-search

Returns paths of video files containing a given phrase.
For each file, additionally sends the whole speech transcript where the phrase was found,
as well as indices of occurrences.

Uses flask for http server and communicates with converter and audio-search
Currently only searches for phrases in videos' audio

### Available at

`http://localhost:9051`

### Request format

`http://localhost:9051/search?rootPath={path}&phrases={phrases}&langs={languages}`

Example

`http://localhost:9051/search?rootPath=/app/files&phrases=lis,fox&lang=pl,eng`

### Example response (success)

`http://localhost:9051/search?rootPath=/app/files&phrases=lis,fox&lang=pl,eng`

```json
{
  "phrases": [
    "lis",
    "fox"
  ],
  "lang": [
    "pl",
    "eng"
  ],
  "status": "ok",
  "results": [
    {
      "filePath": "/app/files/pl_text_eng_audio.mp4",
      "matches": [
        {
          "searchContext": "the quick brown fox jumps over the lazy dog",
          "indices": [
            16
          ]
        }
      ]
    }
  ],
  "errors": []
}
```

### Example response (error)

`http://localhost:9051/search?rootPath=/app/files&phrases=lis,fox&langs=en-US`

```json
{
  "phrases": [
    "lis",
    "fox"
  ],
  "languages": [
    "en-US"
  ],
  "status": "error",
  "results": [],
  "errors": [
    "Each phrase has to be associated with a language"
  ]
}
```
