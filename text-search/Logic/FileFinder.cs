namespace TextSearch.Logic; 

public class FileFinder : IFileFinder {

    public List<FileSearchResult> FindInDirectory(string path, string phrase) => 
        FindInDirectoryHelper(path, phrase).ToList();

    private IEnumerable<FileSearchResult> FindInDirectoryHelper(string path, string phrase) {
            var other = Directory.EnumerateDirectories(path).SelectMany(directory => FindInDirectoryHelper(directory, phrase));
            var current = Directory.EnumerateFiles(path)
               .Where(filePath => filePath.EndsWith(".txt") || filePath.EndsWith(".md"))
               .Select(filePath => FindInFile(filePath, phrase))
               .WhereNotNull();

            return current.Concat(other);
    }

    public FileSearchResult? FindInFile(string path, string phrase) {
        var results = File.ReadLines(path)
            .Select((line, index) => line.IndicesOf(phrase, StringComparison.Ordinal) switch {
                 null => null,
                 var indices => new LineSearchResult(line, indices, index + 1)
            })
            .WhereNotNull()
            .ToList();
        return results.Count > 0 ? new FileSearchResult(path, results) : null;
    }
        
}
