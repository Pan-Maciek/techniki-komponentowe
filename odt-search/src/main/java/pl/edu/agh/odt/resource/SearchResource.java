package pl.edu.agh.odt.resource;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import pl.edu.agh.odt.logic.FileFinder;
import pl.edu.agh.odt.result.FileSearchResult;
import pl.edu.agh.odt.result.RequestResult;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResource extends ServerResource {

    @Get("/search")
    public RequestResult getResults() {

        String rootPath = getQueryValue("rootPath");
        List<String> phrases = getQueryValues("phrases");
        List<String> lang = getQueryValues("lang");

        FileFinder finder = new FileFinder(rootPath, phrases);

        try {
            List<FileSearchResult> results = finder.findInDirectory();
            return new RequestResult(phrases, lang, "ok", results, finder.getExceptions());
        }
        catch (IOException e){
            return new RequestResult(phrases, lang,"error", Collections.emptyList(), List.of(e.getMessage()));
        }

    }

    private List<String> getQueryValues(String parameter){
        return Arrays.stream(getQueryValue(parameter).split(","))
                .map(phrase -> URLDecoder.decode(phrase, StandardCharsets.UTF_8))
                .collect(Collectors.toList());
    }


}
