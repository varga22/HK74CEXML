package domhk74ce1105;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomModifyHK74CE_01 {

    public static void main(String[] args) {
        try {
            File inputFile = new File("OrarendHK74CE.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);
            System.out.println("XML fájl beolvasva sikeresen.");

            doc.getDocumentElement().normalize();

            // Hozzáad egy óraadó elemet, ahol nincs megadva (A., FELADAT)
            NodeList orak = doc.getElementsByTagName("ora");
            for (int i = 0; i < orak.getLength(); i++) {
                Node ora = orak.item(i);

                if (ora.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) ora;

                    // Ellenőrzi, hogy van-e már óraadó elem (egyiknél sincs, mivel a HK74CE_kurzusfelvetel.xml fájlban van)
                    NodeList oradoList = oraElement.getElementsByTagName("óraadó");
                    if (oradoList.getLength() == 0) {
                        Element orado = doc.createElement("óraadó");
                        orado.setTextContent("Gyakorlatvezető oktató");
                        oraElement.appendChild(orado);
                        System.out.println("Óraadó elem hozzáadva az egyik órához.");
                    }
                }
            }

            // Módosítja minden óra típusát gyakorlatról előadásra (B., FELADAT)
            for (int i = 0; i < orak.getLength(); i++) {
                Node ora = orak.item(i);

                if (ora.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) ora;

                    // A típus attribútum módosítása
                    String tipus = oraElement.getAttribute("típus");
                    if ("gyakorlat".equalsIgnoreCase(tipus)) {
                        oraElement.setAttribute("típus", "előadás");
                        System.out.println("Óra típusa gyakorlatról előadásra módosítva.");
                    }
                }
            }

            // Átalakító létrehozása és a módosított dokumentum kiírása konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            System.out.println("\n--- Módosított XML fájl tartalma: ---");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            // Módosított dokumentum kiírása fájlba
            StreamResult fileResult = new StreamResult(new FileOutputStream("orarendModifyHK74CE.xml"));
            transformer.transform(source, fileResult);

            System.out.println("\nMódosított fájl mentve: orarendModifyHK74CE.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
