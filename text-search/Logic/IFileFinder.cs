namespace TextSearch.Logic; 

public record LineSearchResult(string SearchContext, List<int> Indices, int LineNumber);
public record FileSearchResult(string FilePath, List<LineSearchResult> Matches);

public interface IFileFinder {
    public List<FileSearchResult> FindInDirectory(string path, List<string> phrases);
    public FileSearchResult? FindInFile(string path, List<string> phrases);
}
