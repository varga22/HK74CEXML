import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLtransformer {
    public static void main(String[] args) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File("hallgatoHK74CE.xsl")));
            transformer.transform(
                new StreamSource(new File("hallgatoHK74CE.xml")),
                new StreamResult(new File("hallgatoHK74CE.out.html"))
            );
            System.out.println("Átalakítás kész: hallgatoHK74CE.out.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
