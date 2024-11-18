package domhk74ce1105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomQueryHk74ce {
    public static void main(String[] args) {
        try {
            // Az XML dokumentum beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("UjhallgatoHK74CE.xml");

            // A gyökérelem elérése
            Element root = document.getDocumentElement();
            System.out.println("Gyökérelem: " + root.getNodeName());

            // Az összes 'hallgato' elem lekérdezése
            NodeList hallgatoNodes = document.getElementsByTagName("hallgato");
            NodeList oktatoNodes = document.getElementsByTagName("oktato");
            
            System.out.println("Hallgatók vezetéknevei:");
            for (int i = 0; i < hallgatoNodes.getLength(); i++) {
                Node hallgatoNode = hallgatoNodes.item(i);

                if (hallgatoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element hallgatoElement = (Element) hallgatoNode;

                    // A 'vezeteknev' elem lekérdezése
                    NodeList vezeteknevList = hallgatoElement.getElementsByTagName("vezeteknev");
                    if (vezeteknevList.getLength() > 0) {
                        Element vezeteknevElement = (Element) vezeteknevList.item(0);
                        String vezeteknev = vezeteknevElement.getTextContent();
                        System.out.println(vezeteknev);
                    }
                }
            }

            System.out.println("Oktatók vezetéknevei:");
            for (int i = 0; i < oktatoNodes.getLength(); i++) {
                Node oktatoNode = oktatoNodes.item(0);

                if (oktatoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oktatoElement = (Element) oktatoNode;

                    // A 'vezeteknev' elem lekérdezése
                    NodeList vezeteknevList = oktatoElement.getElementsByTagName("vezeteknev");
                    if (vezeteknevList.getLength() > 0) {
                        Element vezeteknevElement = (Element) vezeteknevList.item(0);
                        String vezeteknev = vezeteknevElement.getTextContent();
                        System.out.println(vezeteknev);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
    }
}
