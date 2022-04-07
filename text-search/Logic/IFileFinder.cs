namespace TextSearch.Logic; 

public record LineSearchResult(string SearchContext, List<int> Indices, int LineNumber);
public record FileSearchResult(string FilePath, List<LineSearchResult> Matches);

public interface IFileFinder {
    public List<FileSearchResult> FindInDirectory(string path, string phrase);
    public FileSearchResult? FindInFile(string path, string phrase);
}
