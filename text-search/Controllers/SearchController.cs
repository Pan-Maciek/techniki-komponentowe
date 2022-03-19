namespace TextSearch.Controllers; 

[ApiController]
[Route("/search")]
public class SearchController : ControllerBase {

    private readonly ILogger<SearchController> _logger;

    public SearchController(ILogger<SearchController> logger) {
        _logger = logger;
    }

    [HttpGet]
    public string Get(string path, string query, string fileSearchPattern) {
        _logger.LogInformation("{Path} {SearchPattern} \"{Query}\"", path, fileSearchPattern, query);
        return "";
    }
}
