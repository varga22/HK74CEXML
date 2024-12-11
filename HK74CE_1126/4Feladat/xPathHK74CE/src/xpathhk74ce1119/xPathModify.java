package xpathhk74ce1119;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class xPathModify {
    public static void main(String[] args) {
        try {
            // XML dokumentum beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("OrarendHK74CE.xml");

            // 1. Módosítás: Szak nevének módosítása
            NodeList szakNodes = doc.getElementsByTagName("szak");
            for (int i = 0; i < szakNodes.getLength(); i++) {
                szakNodes.item(i).setTextContent("Mérnökinformatikus");
            }

            // 2. Módosítás: Tárgy nevéhez monogram hozzáfűzése
            NodeList targyNodes = doc.getElementsByTagName("targy");
            for (int i = 0; i < targyNodes.getLength(); i++) {
                String originalText = targyNodes.item(i).getTextContent();
                String oktatoName = ((Element) targyNodes.item(i).getParentNode()).getElementsByTagName("oktato").item(0).getTextContent();
                String monogram = getMonogram(oktatoName);
                targyNodes.item(i).setTextContent(originalText + " (" + monogram + ")");
            }

            // 3. Módosítás: ID=3 helyszínének módosítása
            NodeList oraNodes = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraNodes.getLength(); i++) {
                Element ora = (Element) oraNodes.item(i);
                if (ora.getAttribute("id").equals("03")) {
                    ora.getElementsByTagName("helyszin").item(0).setTextContent("XXXVII előadó");
                }
            }

            // Módosított XML kiírása a konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            // Módosított XML mentése fájlba
            StreamResult fileResult = new StreamResult(new File("OrarendHK74CE1.xml"));
            transformer.transform(source, fileResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMonogram(String name) {
        StringBuilder monogram = new StringBuilder();
        String[] parts = name.split(" ");
        for (String part : parts) {
            if (!part.isEmpty()) {
                monogram.append(part.charAt(0));
            }
        }
        return monogram.toString();
    }
}
