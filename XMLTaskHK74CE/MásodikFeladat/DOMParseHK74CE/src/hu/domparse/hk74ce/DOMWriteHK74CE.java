package hu.domparse.hk74ce;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWriteHK74CE {
    public static void main(String[] args) {
        try {
            // DOM Builder inicializálása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML fájl betöltése
            Document document = builder.parse(new File("XML_HK74CE.xml"));
            document.getDocumentElement().normalize();

            // Fa struktúra kiírása konzolra
            System.out.println("Fa struktúra kiírása konzolra:");
            printNode(document.getDocumentElement(), 0);

            // Mentés új fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XML_HK74CE1.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);

            System.out.println("\nA dokumentum sikeresen mentve az XML_HK74CE1.xml fájlba!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int depth) {
        String indent = "  ".repeat(depth); // Dinamikus behúzás

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            // Elem kezdő tag kiírása
            System.out.print(indent + "<" + node.getNodeName());
            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
            }
            System.out.println(">");
        }

        // Gyerekek feldolgozása
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                String textContent = child.getNodeValue().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(indent + "  " + textContent); // Szöveges tartalom kiírása
                }
            } else {
                printNode(child, depth + 1); // Rekurzív hívás
            }
        }

        // Elem záró tag kiírása
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indent + "</" + node.getNodeName() + ">");
        }
    }

}
