import org.apache.commons.lang3.StringUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws Exception {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        AutoDetectParser parser = new AutoDetectParser();
        ParseContext parseContext = new ParseContext();

        parser.parse(fileToIS("bomb-defusal-manual.pdf"), handler, metadata, parseContext);
        String text = handler.toString();
        System.out.printf("TEXT: %s\n", text);
        System.out.printf("TITLE: %s\n", metadata.get(TikaCoreProperties.TITLE));
        System.out.printf("PAGE COUNT: %s\n", metadata.get("xmpTPg:NPages"));

        System.out.println("==========================");
        String phrase = "matematyka";
        System.out.printf("Is phrase %s present?: %s\n", phrase, StringUtils.containsIgnoreCase(text, phrase));
        phrase = "bomb";
        System.out.printf("Is phrase %s present?: %s\n", phrase, StringUtils.containsIgnoreCase(text, phrase));
    }

    private static InputStream fileToIS(String path) throws FileNotFoundException {
        return Test.class.getResourceAsStream(path);
    }
}
