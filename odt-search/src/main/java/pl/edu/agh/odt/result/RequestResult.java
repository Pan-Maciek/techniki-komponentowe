package pl.edu.agh.odt.result;

import java.util.List;

public final class RequestResult {

    private final String status;
    private final List<FileSearchResult> results;
    private final List<String> errors;


    public RequestResult(String status, List<FileSearchResult> results, List<String> errors) {
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
}
