using System.Web;

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
    public IActionResult Get([FromQuery] SearchRequest request) {
        var rootPath = request.RootPath;
        var phrases = request.Phrases.Select(HttpUtility.UrlDecode).WhereNotNull().ToList();
        
        _logger.LogInformation("Searching in {Path} for *.txt | *.md files containing '{Phrases}'", rootPath, phrases);
        
        var uniquePhrases = phrases.DistinctBy(phrase => phrase.ToLowerInvariant()).ToList();

        try
        {
            var results =  _fileFinder.FindInDirectory(rootPath, uniquePhrases);
            return Ok(new SearchResponse(phrases, results));
        }
        catch (Exception e)
        {
            _logger.LogError("Search in {Path} failed with: {Error}", rootPath, e.Message);
            return Ok(new SearchResponse(phrases, e));
        }
    }
    
    public record SearchRequest(List<string> Phrases, string RootPath);
    
    public record SearchResponse(List<string> Phrases, string Status, List<FileSearchResult> Results, List<string> Errors)
    {
        public SearchResponse(List<string> phrases, List<FileSearchResult> results) 
            : this(phrases, "ok", results, new List<string>()) { }

        public SearchResponse(List<string> phrases, Exception exception) 
            : this(phrases, "error", new List<FileSearchResult>(), new List<string> {exception.Message}) { }
    }
}
