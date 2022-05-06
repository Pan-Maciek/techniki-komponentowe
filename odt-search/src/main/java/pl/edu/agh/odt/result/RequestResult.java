package pl.edu.agh.odt.result;

import java.util.List;

public final class RequestResult {

    private final List<String> phrases;

    private final List<String> lang;
    private final String status;
    private final List<FileSearchResult> results;
    private final List<String> errors;


    public RequestResult(List<String> phrases, List<String> lang, String status, List<FileSearchResult> results, List<String> errors) {
        this.phrases = phrases;
        this.lang = lang;
        this.status = status;
        this.results = results;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public List<FileSearchResult> getResults() {
        return results;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "status='" + status + '\'' +
                ", results=" + results +
                ", errors=" + errors +
                '}';
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public List<String> getLang() {
        return lang;
    }
}
