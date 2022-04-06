package pl.edu.agh.odt.result;

import java.util.List;

public final class FileSearchResult {

    private final String path;
    private final List<ParagraphSearchResult> paragraphSearchResults;

    public FileSearchResult(String path, List<ParagraphSearchResult> paragraphSearchResults) {
        this.path = path;
        this.paragraphSearchResults = paragraphSearchResults;
    }

    public String getFilePath() {
        return path;
    }

    public List<ParagraphSearchResult> getMatches() {
        return paragraphSearchResults;
    }

    @Override
    public String toString() {
        return "FileSearchResult{" +
                "path='" + path + '\'' +
                ", paragraphSearchResults=" + paragraphSearchResults +
                '}';
    }
}
