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

    public List<FileSearchResult> FindInDirectory(string path, List<string> phrases) =>
        Matcher.GetResultsInFullPath(path)
            .Select(file => FindInFile(file, phrases))
            .WhereNotNull()
            .ToList();

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
