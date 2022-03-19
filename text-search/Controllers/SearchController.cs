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
    public List<FileSearchResult> Get(string path, string query, string fileSearchPattern) {
        _logger.LogInformation("{Path} {SearchPattern} \"{Query}\"", path, fileSearchPattern, query);
        return _fileFinder.FindInDirectory(path, fileSearchPattern, query);
    }
}
