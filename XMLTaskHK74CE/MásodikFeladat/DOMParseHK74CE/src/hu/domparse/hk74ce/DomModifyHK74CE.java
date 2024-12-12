package hu.domparse.hk74ce;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyHK74CE {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("XML_HK74CE.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Dokumentum normalizálása
            doc.getDocumentElement().normalize();
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // 1. módosítás: Diák e-mail cím hozzáadása
            System.out.println("\n1. módosítás: Diák e-mail cím hozzáadása.");
            NodeList diakok = doc.getElementsByTagName("Diak");
            for (int i = 0; i < diakok.getLength(); i++) {
                Node node = diakok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element diak = (Element) node;
                    if (diak.getElementsByTagName("DiakID").item(0).getTextContent().equals("HK74CE")) {
                        Element ujEmail = doc.createElement("Email");
                        ujEmail.setTextContent("uj.email@example.com");
                        diak.appendChild(ujEmail);
                        System.out.println("Új e-mail cím hozzáadva a diákhoz: HK74CE");
                        break;
                    }
                }
            }

            // 2. módosítás: Kurzus létszámának növelése
            System.out.println("\n2. módosítás: Kurzus létszámának növelése.");
            NodeList kurzusok = doc.getElementsByTagName("Kurzus");
            for (int i = 0; i < kurzusok.getLength(); i++) {
                Node node = kurzusok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element kurzus = (Element) node;
                    if (kurzus.getElementsByTagName("KurzusID").item(0).getTextContent().equals("KURZ001")) {
                        int jelenlegiLetszam = Integer.parseInt(kurzus.getElementsByTagName("Letszam").item(0).getTextContent());
                        kurzus.getElementsByTagName("Letszam").item(0).setTextContent(String.valueOf(jelenlegiLetszam + 5));
                        System.out.println("Kurzus KURZ001 létszáma növelve: " + (jelenlegiLetszam + 5));
                        break;
                    }
                }
            }

            // 3. módosítás: Egy tantárgy kreditértékének módosítása
            System.out.println("\n3. módosítás: Egy tantárgy kreditértékének módosítása.");
            NodeList tantargyak = doc.getElementsByTagName("Tantargy");
            for (int i = 0; i < tantargyak.getLength(); i++) {
                Node node = tantargyak.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tantargy = (Element) node;
                    if (tantargy.getElementsByTagName("TargyID").item(0).getTextContent().equals("MAT101")) {
                        tantargy.getElementsByTagName("Kredit").item(0).setTextContent("3");
                        System.out.println("Tantárgy MAT101 kreditértéke módosítva: 3");
                        break;
                    }
                }
            }

            // 4. módosítás: Egy terem nevének átírása
            System.out.println("\n4. módosítás: Egy terem nevének átírása.");
            NodeList termek = doc.getElementsByTagName("Terem");
            for (int i = 0; i < termek.getLength(); i++) {
                Node node = termek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element terem = (Element) node;
                    if (terem.getElementsByTagName("TeremID").item(0).getTextContent().equals("37")) {
                        terem.getElementsByTagName("Nev").item(0).setTextContent("harminchetes előadó");
                        System.out.println("Terem ID (37) neve módosítva: harminchetes előadó");
                        break;
                    }
                }
            }

            // Módosított dokumentum mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XML_HK74CE_Modified.xml"));
            transformer.transform(source, result);

            System.out.println("\nA módosított XML fájl mentve: XML_HK74CE_Modified.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
