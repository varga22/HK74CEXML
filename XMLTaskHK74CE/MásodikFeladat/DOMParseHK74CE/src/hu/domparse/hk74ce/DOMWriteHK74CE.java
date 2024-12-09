package hu.domparse.hk74ce;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteHK74CE {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("XML_HK74CE.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Dokumentum normalizálása
            doc.getDocumentElement().normalize();

            // Gyökérelem kiírása
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // XML tartalom kiírása a konzolra fa struktúrában
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formázott kiírás
            DOMSource source = new DOMSource(doc);

            // Konzolra írás
            System.out.println("\nXML Tartalom fa struktúra formában:");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            // Tartalom mentése új fájlba
            StreamResult fileResult = new StreamResult(new File("XML_HK74CE_1.xml"));
            transformer.transform(source, fileResult);

            System.out.println("\nAz XML tartalom sikeresen mentve az 'XML_HK74CE_1.xml' fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
