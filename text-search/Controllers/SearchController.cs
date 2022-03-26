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
    public List<FileSearchResult> Get(string phrase, string rootPath) {
        _logger.LogInformation("Searching in {Path} for *.txt | *.md files containing '{Phrase}'", rootPath, phrase);

        return _fileFinder.FindInDirectory(rootPath, phrase);
    }
}
