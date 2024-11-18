package domHK74CE1022;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMWriteHK74CE1 {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        try {
            // XML fájl beolvasása
            File inputFile = new File("orarendHK74CE.xml");

            // Dokumentum példányosítása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            // Normalizáljuk a dokumentumot
            doc.getDocumentElement().normalize();

            // Gyökérelem kiírása
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Fastruktúra kiírása konzolra
            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Node ora = oraList.item(i);

                if (ora.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) ora;
                    System.out.println("Óra ID: " + oraElement.getAttribute("id"));
                    System.out.println("Típus: " + oraElement.getAttribute("típus"));
                    System.out.println("Tantárgy: " + oraElement.getElementsByTagName("targy").item(0).getTextContent());
                    System.out.println("Időpont: ");
                    System.out.println("\tNap: " + oraElement.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("\tTól: " + oraElement.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("\tIg: " + oraElement.getElementsByTagName("ig").item(0).getTextContent());
                    System.out.println("Helyszín: " + oraElement.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktató: " + oraElement.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: " + oraElement.getElementsByTagName("szak").item(0).getTextContent());
                    System.out.println("-------------------------");
                }
            }

            // Új fájl létrehozása
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Kimeneti formázás
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Dokumentum mentése új fájlba
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Orarend1HK74CE.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
