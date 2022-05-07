using Microsoft.Extensions.FileSystemGlobbing;

namespace TextSearch.Logic; 

public class FileFinder : IFileFinder
{
    private static readonly Matcher Matcher;
    
    static FileFinder()
    {
        Matcher = new Matcher()
            .AddInclude("**/*.txt")
            .AddInclude("**/*.md");
    }

    public List<FileSearchResult> FindInDirectory(string path, string phrase) =>
        Matcher.GetResultsInFullPath(path)
            .Select(file => FindInFile(file, phrase))
            .WhereNotNull()
            .ToList();

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
