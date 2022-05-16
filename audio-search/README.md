# audio-search

Returns paths of .wav files containing a given phrase.
For each file, additionally sends the whole speech transcript where the phrase was found,
as well as indices of occurrences.

Uses SpeechRecognition for speech recognition and flask for http server

### Available at

`http://localhost:9031`

### Request format

`http://localhost:9031/search?rootPath={path}&phrases={phrases}&langs={languages}`

(Used by video-search) `http://localhost:9031/no_conversion_search?rootPath={path}&phrases={phrases}&langs={languages}`

Example

`http://localhost:9031/search?rootPath=/app/files&phrases=jest,key&lang=pl,eng`

### Example response (success)

`http://localhost:9031/search?rootPath=/app/files&phrases=jest,key&lang=pl,eng`

```json
{
  "phrases": [
    "jest",
    "key"
  ],
  "lang": [
    "pl",
    "eng"
  ],
  "status": "ok",
  "results": [
    {
      "filePath": "/app/files/kret_d042.wav",
      "matches": [
        {
          "searchContext": "Sandy key",
          "indices": [
            6
          ]
        }
      ]
    },
    {
      "filePath": "/app/files/kret_d044.wav",
      "matches": [
        {
          "searchContext": "No przecież jest zobacz na długości cienia i te kolory zachód Jak malowany",
          "indices": [
            12
          ]
        }
      ]
    }
  ],
  "errors": []
}
```

### Example response (error)

`http://localhost:9031/search?rootPath=/app/files&phrases=who,kto&langs=en-US`

```json
{
  "phrases": [
    "who",
    "kto"
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
