package domhk74ce1105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMQuery {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("OrarendHK74CE.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Gyökérelem kiírása
            Element root = document.getDocumentElement();
            System.out.println("Gyökérelem: " + root.getNodeName());

            // a) Kurzusok nevének lekérdezése
            List<String> kurzusok = getKurzusok(document);
            System.out.println("Kurzusnevek: " + kurzusok);

            // Első kurzus kiírása
            String elsoOra = getElsoOra(document);
            System.out.println("Első kurzus strukturált formában:\n" + elsoOra);
            writeToFile("elso_kurzus.txt", elsoOra);

            // c) Oktatók neveinek lekérdezése
            List<String> oktatok = getOktatok(document);
            System.out.println("Oktatók nevei: " + oktatok);

            // d) Összetett lekérdezés: Minden szerdai óra neve és helyszíne
            List<String> szerdaiOrak = getSzerdaiOrak(document);
            System.out.println("Szerdai órák nevei és helyszínei: " + szerdaiOrak);

        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // a) Kurzusok nevének lekérdezése
    private static List<String> getKurzusok(Document document) {
        List<String> kurzusok = new ArrayList<>();
        NodeList oraNodes = document.getElementsByTagName("ora");
        for (int i = 0; i < oraNodes.getLength(); i++) {
            Element oraElement = (Element) oraNodes.item(i);
            String targy = oraElement.getElementsByTagName("targy").item(0).getTextContent();
            kurzusok.add(targy);
        }
        return kurzusok;
    }

    // b) Első kurzus strukturált kiírása
    private static String getElsoOra(Document document) {
        NodeList oraNodes = document.getElementsByTagName("ora");
        if (oraNodes.getLength() > 0) {
            Element oraElement = (Element) oraNodes.item(0);
            return formatOra(oraElement);
        }
        return "Nincs óra az XML dokumentumban.";
    }

    private static void writeToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Nem sikerült a fájlba írás: " + e.getMessage());
        }
    }

    // c) Oktatók neveinek lekérdezése
    private static List<String> getOktatok(Document document) {
        List<String> oktatok = new ArrayList<>();
        NodeList oraNodes = document.getElementsByTagName("ora");
        for (int i = 0; i < oraNodes.getLength(); i++) {
            Element oraElement = (Element) oraNodes.item(i);
            String oktato = oraElement.getElementsByTagName("oktato").item(0).getTextContent();
            if (!oktatok.contains(oktato)) {
                oktatok.add(oktato);
            }
        }
        return oktatok;
    }

    // d) Szerdai órák nevei és helyszínei
    private static List<String> getSzerdaiOrak(Document document) {
        List<String> szerdaiOrak = new ArrayList<>();
        NodeList oraNodes = document.getElementsByTagName("ora");
        for (int i = 0; i < oraNodes.getLength(); i++) {
            Element oraElement = (Element) oraNodes.item(i);
            String nap = oraElement.getElementsByTagName("nap").item(0).getTextContent();
            if ("Szerda".equals(nap)) {
                String targy = oraElement.getElementsByTagName("targy").item(0).getTextContent();
                String helyszin = oraElement.getElementsByTagName("helyszin").item(0).getTextContent();
                szerdaiOrak.add(targy + " - " + helyszin);
            }
        }
        return szerdaiOrak;
    }

    // Egy kurzus strukturált formázása
    private static String formatOra(Element oraElement) {
        StringBuilder formatted = new StringBuilder();
        formatted.append("ID: ").append(oraElement.getAttribute("id")).append("\n");
        formatted.append("Típus: ").append(oraElement.getAttribute("típus")).append("\n");
        formatted.append("Tárgy: ").append(oraElement.getElementsByTagName("targy").item(0).getTextContent()).append("\n");
        formatted.append("Nap: ").append(oraElement.getElementsByTagName("nap").item(0).getTextContent()).append("\n");
        formatted.append("Idő: ")
                .append(oraElement.getElementsByTagName("tol").item(0).getTextContent())
                .append(" - ")
                .append(oraElement.getElementsByTagName("ig").item(0).getTextContent())
                .append("\n");
        formatted.append("Helyszín: ").append(oraElement.getElementsByTagName("helyszin").item(0).getTextContent()).append("\n");
        formatted.append("Oktató: ").append(oraElement.getElementsByTagName("oktato").item(0).getTextContent()).append("\n");
        formatted.append("Szak: ").append(oraElement.getElementsByTagName("szak").item(0).getTextContent()).append("\n");
        return formatted.toString();
    }
}
