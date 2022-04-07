# backend

Returns a map of microservices names and their responses. 
If the given path does not exist, returns a map with single entry - key is "backend" 
and value contains information about the error.

### Available at 

`http://localhost:8080`

### Request format

`http://localhost:8080/search?rootPath={path}&phrase={phrase}&additionalInfo.enabledFormats={service1, service2}`

Example

`http://localhost:8080/search?rootPath=/app/files&phrase=bells&additionalInfo.enabledFormats=text-search`

### Example response (if rootPath exists)

```json
{
    "text-search": {
        "phrase": "bells",
        "status": "ok",
        "results": [
            {
                "filePath": "/app/files/file1.txt",
                "matches": [
                    {
                        "searchContext": "Some bells",
                        "indices": [
                            5
                        ],
                        "lineNumber": 142
                    }
                ]
            }
        ],
        "errors": []
    }
}
```

### Example response (if rootPath does not exist)

```json
{
    "backend": {
        "path": "/app/dir",
        "status": "error",
        "results": [],
        "errors": [
            "The given path does not exists."
        ]
    }
}
```


