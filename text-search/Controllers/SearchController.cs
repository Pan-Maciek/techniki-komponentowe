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
        var phrases = request.Phrases.Split(',').Select(HttpUtility.UrlDecode).WhereNotNull().ToList();
        var lang = request.Lang.Split(',').ToList();
        
        _logger.LogInformation("Searching in {Path} for *.txt | *.md files containing '{Phrases}'", rootPath, phrases);
        
        var uniquePhrases = phrases.DistinctBy(phrase => phrase.ToLowerInvariant()).ToList();

        try
        {
            var results =  _fileFinder.FindInDirectory(rootPath, uniquePhrases);
            return Ok(new SearchResponse(phrases, lang, results));
        }
        catch (Exception e)
        {
            _logger.LogError("Search in {Path} failed with: {Error}", rootPath, e.Message);
            return Ok(new SearchResponse(phrases, lang, e));
        }
    }
    
    public record SearchRequest(string Phrases, string Lang, string RootPath);
    
    public record SearchResponse(List<string> Phrases, List<string> Lang, string Status, List<FileSearchResult> Results, List<string> Errors)
    {
        public SearchResponse(List<string> phrases, List<string> lang, List<FileSearchResult> results) 
            : this(phrases, lang, "ok", results, new List<string>()) { }

        public SearchResponse(List<string> phrases, List<string> lang, Exception exception) 
            : this(phrases, lang, "error", new List<FileSearchResult>(), new List<string> {exception.Message}) { }
    }
}
