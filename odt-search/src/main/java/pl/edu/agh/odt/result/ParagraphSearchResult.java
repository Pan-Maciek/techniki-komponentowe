package pl.edu.agh.odt.result;

import java.util.ArrayList;
import java.util.List;

public final class ParagraphSearchResult {

    private final String searchContext;
    private final List<Integer> indices;

    public ParagraphSearchResult(String searchContext) {
        this.searchContext = searchContext;
        this.indices = new ArrayList<>();
    }

    public String getSearchContext() {
        return searchContext;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    @Override
    public String toString() {
        return "ParagraphSearchResult{" +
                "searchContext='" + searchContext + '\'' +
                ", indices=" + indices +
                '}';
    }
}
