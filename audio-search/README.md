# pdf-search

Returns paths of .wav files containing a given phrase.
For each file, additionally sends the whole speech transcript where the phrase was found,
as well as indices of occurrences.

Uses SpeechRecognition for speech recognition and flask for http server

### Available at

`http://localhost:9031`

### Request format

`http://localhost:9031/search?rootPath={path}&phrases={phrases}&langs={languages}`

Example

`http://localhost:9031/search?rootPath=/app/files&phrases=drzewo&langs=pl`

### Example response (success)

`http://localhost:9031/search?rootPath=/app/files&phrases=who,kto&langs=en-US,pl-PL`

```json
{
  "phrases": [
    "who",
    "kto"
  ],
  "languages": [
    "en-US",
    "pl-PL"
  ],
  "status": "ok",
  "results": [
    {
      "path": "app/files/harvard.wav",
      "matches": [
        {
          "search_context": "the mute muffled the hi-tones of the who warned the gold ring fits only appeared ear the old pan was covered with hard fudge what's the log float in the wide river the node on the stalk of wheat grew daily the Heap of fallen leaves was set on fire right fast if you want to finish early his shirt was clean but one button was gone the barrel of beer was a brew of malt and hops tin cans are absent from store shelves",
          "indices": [
            37
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
