import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLtranformer {
    public static void main(String[] args) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File("orarendHK74CE.xsl")));
            transformer.transform(
                new StreamSource(new File("orarendHK74CE.xml")),
                new StreamResult(new File("orarendHK74CE.out.html"))
            );
            System.out.println("Átalakítás kész: orarendHK74CE.out.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
