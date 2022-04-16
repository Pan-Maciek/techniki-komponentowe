namespace TextSearch.Logic; 

public class FileFinder : IFileFinder {

    public List<FileSearchResult> FindInDirectory(string path, List<string> phrases) => 
        FindInDirectoryHelper(path, phrases).ToList();

    private IEnumerable<FileSearchResult> FindInDirectoryHelper(string path, List<string> phrases) {
            var other = Directory.EnumerateDirectories(path).SelectMany(directory => FindInDirectoryHelper(directory, phrases));
            var current = Directory.EnumerateFiles(path)
               .Where(filePath => filePath.EndsWith(".txt") || filePath.EndsWith(".md"))
               .Select(filePath => FindInFile(filePath, phrases))
               .WhereNotNull();

            return current.Concat(other);
    }

    public FileSearchResult? FindInFile(string path, List<string> phrases) {
        var results = File.ReadLines(path)
            .Select((line, index) => 
                line.IndicesOfAny(phrases, StringComparison.Ordinal) switch {
                 null => null,
                 var indices => new LineSearchResult(line, indices, index + 1)
            })
            .WhereNotNull()
            .ToList();
        return results.Count > 0 ? new FileSearchResult(path, results) : null;
    }
        
}
