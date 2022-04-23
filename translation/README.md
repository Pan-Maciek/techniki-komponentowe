# backend

### Request format (/translate)

`http://translation/translate?phrase={phrase}&lang={lang1,lang2}`

Example

`http://translation/translate?phrase=dom&lang=en,de`

### Example response

```json
["dom", "house", "Hause"]
```

### Request format (/languages)

`http://translation/translate?phrase={phrase}&lang={lang1,lang2}`

Example

`http://translation/translate?phrase=dom&lang=en,de`

### Example response

```json
{
  "af": "Afrikaans",
  "sq": "Albanian",
  "ar": "Arabic",
  "hy": "Armenian",
  // ...
}
```