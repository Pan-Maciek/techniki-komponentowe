namespace TextSearch.Logic; 

public class FileFinder : IFileFinder {

    public List<FileSearchResult> FindInDirectory(string path, string query) => 
        FindInDirectoryHelper(path, query).ToList();

    private IEnumerable<FileSearchResult> FindInDirectoryHelper(string path, string query) {
            var other = Directory.EnumerateDirectories(path).SelectMany(directory => FindInDirectoryHelper(directory, query));
            var current = Directory.EnumerateFiles(path, "*.md")
               .Where(filePath => filePath.EndsWith(".txt") || filePath.EndsWith(".md"))
               .Select(filePath => FindInFile(filePath, query))
               .WhereNotNull();

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
