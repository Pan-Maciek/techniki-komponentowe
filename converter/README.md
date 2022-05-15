# converter

Service for any needed conversions and extractions. It creates temporary directory with temporary converted files and handles its cleanup

### Available at

`http://localhost:9041`

### Available conversions

##### audio

- mp3 -> wav

##### video

- (TBD) mp4 -> wav

### available requests

##### audio_to_wav

- `http://localhost:9041/audio_to_wav?rootPath={path}`

Converts all files starting from rootPath and puts in a tmp directory created in rootPath directory.
 Then returns map `tmp_path -> original_path` of converted files

##### cleanup_audio

- `http://localhost:9041/cleanup_audio?rootPath={path}`

Removes tmp directory created by audio_to_wav request

### Examples

- `http://localhost:9041/audio_to_wav?rootPath=/app/files/`

```json
{
  "pathMap": {
    "/app/files/audio_conversion_results_6dsa8ba9v84a/en.wav": "/app/files/en.mp3",
    "/app/files/audio_conversion_results_6dsa8ba9v84a/pl.wav": "/app/files/pl.mp3",
  },
  "status": "ok",
  "errors": []
}
```

- `http://localhost:9041/cleanup_audio?rootPath=/app/files`