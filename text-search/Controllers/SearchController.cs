namespace TextSearch.Controllers; 

[ApiController]
[Route("/search")]
public class SearchController : ControllerBase {

    private readonly ILogger<SearchController> _logger;
    private readonly IFileFinder _fileFinder;

    public SearchController(ILogger<SearchController> logger, IFileFinder fileFinder) {
        _logger = logger;
        _fileFinder = fileFinder;
    }

    [HttpGet]
    public IActionResult Get(string phrase, string rootPath) {
        _logger.LogInformation("Searching in {Path} for *.txt | *.md files containing '{Phrase}'", rootPath, phrase);

        try
        {
            var results =  _fileFinder.FindInDirectory(rootPath, phrase);
            return Ok(new SearchResponse(results));
        }
        catch (Exception e)
        {
            _logger.LogInformation("Search in {Path} failed with: {Error}", rootPath, e.Message);
            return Ok(new SearchResponse(e));
        }
    }
}

public class SearchResponse
{
    public string Status { get; }
    public List<FileSearchResult> Results { get; } 
    public List<string> Errors { get; }

    public SearchResponse(List<FileSearchResult> results)
    {
        Status = "ok";
        Results = results;
        Errors = new List<string>();
    }

    public SearchResponse(Exception exception)
    {
        Status = "error";
        Results = new List<FileSearchResult>();
        Errors = new List<string> {exception.Message};
    }
}
