package pl.edu.agh.odt.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import pl.edu.agh.odt.logic.FileFinder;
import pl.edu.agh.odt.result.FileSearchResult;
import pl.edu.agh.odt.result.RequestResult;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SearchResource extends ServerResource {

    @Get("/search")
    public RequestResult getResults() {

        String rootPath = getQueryValue("rootPath");
        String phrase = getQueryValue("phrase");

        FileFinder finder = new FileFinder(rootPath, phrase);

        try {
            List<FileSearchResult> results = finder.findInDirectory();
            return new RequestResult(phrase, "ok", results, finder.getExceptions());
        }
        catch (IOException e){
            return new RequestResult(phrase,"error", Collections.emptyList(), List.of(e.getMessage()));
        }

    }


}
