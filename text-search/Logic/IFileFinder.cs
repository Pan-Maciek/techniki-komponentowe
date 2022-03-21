namespace TextSearch.Logic; 

public record LineSearchResult(string SearchContext, List<int> Indices, int LineNumber);
public record FileSearchResult(string FilePath, List<LineSearchResult> Results);

public interface IFileFinder {
    public List<FileSearchResult> FindInDirectory(string path, string query);
    public FileSearchResult? FindInFile(string path, string query);
}
