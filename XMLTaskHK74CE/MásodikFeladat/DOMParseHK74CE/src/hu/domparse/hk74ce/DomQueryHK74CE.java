package hu.domparse.hk74ce;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomQueryHK74CE {
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

            // 1. lekérdezés: Minden diák neve és lakcíme
            System.out.println("\n1. Minden diák neve és lakcme:");
            NodeList diakok = doc.getElementsByTagName("Diak");
            for (int i = 0; i < diakok.getLength(); i++) {
                Node node = diakok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element diak = (Element) node;
                    String keresztnev = diak.getElementsByTagName("Vezeteknev").item(0).getTextContent();
                    String vezeteknev = diak.getElementsByTagName("Keresztnev").item(0).getTextContent();
                    String varos = diak.getElementsByTagName("Varos").item(0).getTextContent();
                    String utca = diak.getElementsByTagName("Utca").item(0).getTextContent();
                    String hazszam = diak.getElementsByTagName("Hazszam").item(0).getTextContent();
                    System.out.printf("Név: %s %s, Lakcím: %s, %s, %s\n", keresztnev, vezeteknev, varos, utca, hazszam);
                }
            }

            // 2. lekérdezés: Minden kurzus ID-je és létszáma
            System.out.println("\n2. Minden kurzus ID-je és létszáma:");
            NodeList kurzusok = doc.getElementsByTagName("Kurzus");
            for (int i = 0; i < kurzusok.getLength(); i++) {
                Node node = kurzusok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element kurzus = (Element) node;
                    String kurzusID = kurzus.getElementsByTagName("KurzusID").item(0).getTextContent();
                    String letszam = kurzus.getElementsByTagName("Letszam").item(0).getTextContent();
                    System.out.printf("Kurzus ID: %s, Létszám: %s\n", kurzusID, letszam);
                }
            }

            // 3. lekérdezés: Kapacitás alapján szűrt termek (kapacitás > 100)
            System.out.println("\n3. Kapacitás alapján szűrt termek (kapacitás > 100):");
            NodeList termek = doc.getElementsByTagName("Terem");
            for (int i = 0; i < termek.getLength(); i++) {
                Node node = termek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element terem = (Element) node;
                    int kapacitas = Integer.parseInt(terem.getElementsByTagName("Kapacitas").item(0).getTextContent());
                    if (kapacitas > 50) {
                        String teremID = terem.getElementsByTagName("TeremID").item(0).getTextContent();
                        String nev = terem.getElementsByTagName("Nev").item(0).getTextContent();
                        System.out.printf("Terem ID: %s, Név: %s, Kapacitás: %d\n", teremID, nev, kapacitas);
                    }
                }
            }

            // 4. lekérdezés: Tantárgyak, amelyeket angol nyelven is oktatnak
            System.out.println("\n4. Tantárgyak, amelyeket angol nyelven is oktatnak:");
            NodeList tantargyak = doc.getElementsByTagName("Tantargy");
            for (int i = 0; i < tantargyak.getLength(); i++) {
                Node node = tantargyak.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tantargy = (Element) node;
                    NodeList nyelvek = tantargy.getElementsByTagName("Nyelv");
                    for (int j = 0; j < nyelvek.getLength(); j++) {
                        if (nyelvek.item(j).getTextContent().equalsIgnoreCase("angol")) {
                            String targyID = tantargy.getElementsByTagName("TargyID").item(0).getTextContent();
                            String nev = tantargy.getElementsByTagName("Nev").item(0).getTextContent();
                            System.out.printf("Tantárgy ID: %s, Név: %s\n", targyID, nev);
                            break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
