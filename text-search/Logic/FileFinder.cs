namespace TextSearch.Logic; 

public class FileFinder : IFileFinder {

    public List<FileSearchResult> FindInDirectory(string path, string fileSearchPattern, string query) => 
        FindInDirectoryHelper(path, fileSearchPattern, query).ToList();

    private IEnumerable<FileSearchResult> FindInDirectoryHelper(string path, string fileSearchPattern, string query) {
            var other = Directory.EnumerateDirectories(path).SelectMany(directory => FindInDirectoryHelper(directory, fileSearchPattern, query));
            var current = Directory.EnumerateFiles(path, fileSearchPattern).Select(filePath => FindInFile(filePath, query)).WhereNotNull();

            return current.Concat(other);
    }

    public FileSearchResult? FindInFile(string path, string query) {
        var results = File.ReadLines(path)
            .Select((line, index) => line.IndicesOf(query, StringComparison.Ordinal) switch {
                 null => null,
                 var indices => new LineSearchResult(line, indices, index + 1)
            })
            .WhereNotNull()
            .ToList();
        return results.Count > 0 ? new FileSearchResult(path, results) : null;
    }
        
}
