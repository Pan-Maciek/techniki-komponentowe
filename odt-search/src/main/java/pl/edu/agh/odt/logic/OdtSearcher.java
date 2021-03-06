package pl.edu.agh.odt.logic;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;
import org.odftoolkit.odfdom.pkg.OdfElement;
import pl.edu.agh.odt.result.FileSearchResult;
import pl.edu.agh.odt.result.ParagraphSearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class OdtSearcher {

    static FileSearchResult phraseOccurrences(List<String> phrases, String path) throws Exception {

        OdfTextDocument textdoc = OdfTextDocument.loadDocument(path);
        TextNavigation navigation = new TextNavigation(Pattern.compile(String.join("|", phrases), Pattern.CASE_INSENSITIVE), textdoc);

        Map<OdfElement, ParagraphSearchResult> results = new HashMap<>();

        while (navigation.hasNext()) {
            TextSelection searchResult = (TextSelection) navigation.getCurrentItem();
            OdfElement odfElement = searchResult.getElement();

            ParagraphSearchResult paragraphResult = results.getOrDefault(odfElement, new ParagraphSearchResult(odfElement.getTextContent()));
            paragraphResult.getIndices().add(searchResult.getIndex());
            results.put(odfElement, paragraphResult);
        }

        if(results.isEmpty()) return null;
        return new FileSearchResult(path, new ArrayList<>(results.values()));
    }
}
