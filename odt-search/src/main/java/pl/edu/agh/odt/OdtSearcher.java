package pl.edu.agh.odt;


import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;

public class OdtSearcher {

    public static void main(String[] args) throws Exception
    {
        OdfTextDocument textdoc = OdfTextDocument.loadDocument(
            OdtSearcher.class.getClassLoader().getResourceAsStream("bells.odt")
        );

        TextNavigation search1;

        search1 = new TextNavigation("bells",textdoc);

        while (search1.hasNext()) {

            TextSelection item1 = (TextSelection) search1.getCurrentItem();

            System.out.println(item1);

        }
    }

}
