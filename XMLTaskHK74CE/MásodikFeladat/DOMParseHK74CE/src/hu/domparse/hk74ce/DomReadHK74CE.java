package hu.domparse.hk74ce;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DomReadHK74CE {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("XML_HK74CE.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Gyökérelem feldolgozása
            doc.getDocumentElement().normalize();
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // Eredmény mentése fájlba
            PrintWriter writer = new PrintWriter(new FileWriter("DOMReadHK74CE_Output.txt"));

            // Diákok feldolgozása
            NodeList diakok = doc.getElementsByTagName("Diak");
            writer.println("Diákok adatai:\n");
            System.out.println("\nDiákok adatai:");
            for (int i = 0; i < diakok.getLength(); i++) {
                Node node = diakok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element diak = (Element) node;

                    // Diák adatainak kiírása
                    String diakID = diak.getElementsByTagName("DiakID").item(0).getTextContent();
                    String keresztnev = diak.getElementsByTagName("Vezeteknev").item(0).getTextContent();
                    String vezeteknev = diak.getElementsByTagName("Keresztnev").item(0).getTextContent();
                    String szulDatum = diak.getElementsByTagName("SzulDatum").item(0).getTextContent();
                    String lakcimVaros = diak.getElementsByTagName("Varos").item(0).getTextContent();
                    String lakcimUtca = diak.getElementsByTagName("Utca").item(0).getTextContent();
                    String lakcimHazszam = diak.getElementsByTagName("Hazszam").item(0).getTextContent();

                    String output = String.format(
                        "Diák ID: %s\nNév: %s %s\nSzületési dátum: %s\nLakcím: %s, %s, %s\n",
                        diakID, keresztnev, vezeteknev, szulDatum, lakcimVaros, lakcimUtca, lakcimHazszam
                    );

                    System.out.println(output);
                    writer.println(output);
                }
            }

            // Tanárok feldolgozása
            NodeList tanarok = doc.getElementsByTagName("Tanar");
            writer.println("\nTanárok adatai:\n");
            System.out.println("\nTanárok adatai:");
            for (int i = 0; i < tanarok.getLength(); i++) {
                Node node = tanarok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tanar = (Element) node;

                    // Tanár adatainak kiírása
                    String tanarID = tanar.getElementsByTagName("TanarID").item(0).getTextContent();
                    String keresztnev = tanar.getElementsByTagName("Keresztnev").item(0).getTextContent();
                    String vezeteknev = tanar.getElementsByTagName("Vezeteknev").item(0).getTextContent();
                    String beosztas = tanar.getElementsByTagName("Beosztas").item(0).getTextContent();
                    String szakterulet = tanar.getElementsByTagName("Szakterulet").item(0).getTextContent();

                    String output = String.format(
                        "Tanár ID: %s\nNév: %s %s\nBeosztás: %s\nSzakterület: %s\n",
                        tanarID, keresztnev, vezeteknev, beosztas, szakterulet
                    );

                    System.out.println(output);
                    writer.println(output);
                }
            }

            // Tantárgyak feldolgozása
            NodeList tantargyak = doc.getElementsByTagName("Tantargy");
            writer.println("\nTantárgyak adatai:\n");
            System.out.println("\nTantárgyak adatai:");
            for (int i = 0; i < tantargyak.getLength(); i++) {
                Node node = tantargyak.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tantargy = (Element) node;

                    // Tantárgy adatainak kiírása
                    String targyID = tantargy.getElementsByTagName("TargyID").item(0).getTextContent();
                    String nev = tantargy.getElementsByTagName("Nev").item(0).getTextContent();
                    String kredit = tantargy.getElementsByTagName("Kredit").item(0).getTextContent();
                    NodeList nyelvek = tantargy.getElementsByTagName("Nyelv");

                    StringBuilder nyelvLista = new StringBuilder();
                    for (int j = 0; j < nyelvek.getLength(); j++) {
                        nyelvLista.append(nyelvek.item(j).getTextContent());
                        if (j < nyelvek.getLength() - 1) {
                            nyelvLista.append(", ");
                        }
                    }

                    String output = String.format(
                        "Tantárgy ID: %s\nNév: %s\nKredit: %s\nNyelv: %s\n",
                        targyID, nev, kredit, nyelvLista
                    );

                    System.out.println(output);
                    writer.println(output);
                }
            }

            // Termek feldolgozása
            NodeList termek = doc.getElementsByTagName("Terem");
            writer.println("\nTermek adatai:\n");
            System.out.println("\nTermek adatai:");
            for (int i = 0; i < termek.getLength(); i++) {
                Node node = termek.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element terem = (Element) node;

                    // Terem adatainak kiírása
                    String teremID = terem.getElementsByTagName("TeremID").item(0).getTextContent();
                    String nev = terem.getElementsByTagName("Nev").item(0).getTextContent();
                    String kapacitas = terem.getElementsByTagName("Kapacitas").item(0).getTextContent();
                    NodeList felszerelesek = terem.getElementsByTagName("Felszereles");

                    StringBuilder felszerelesLista = new StringBuilder();
                    for (int j = 0; j < felszerelesek.getLength(); j++) {
                        felszerelesLista.append(felszerelesek.item(j).getTextContent());
                        if (j < felszerelesek.getLength() - 1) {
                            felszerelesLista.append(", ");
                        }
                    }

                    String output = String.format(
                        "Terem ID: %s\nNév: %s\nKapacitás: %s\nFelszerelés: %s\n",
                        teremID, nev, kapacitas, felszerelesLista
                    );

                    System.out.println(output);
                    writer.println(output);
                }
            }

            // Kurzusok feldolgozása
            NodeList kurzusok = doc.getElementsByTagName("Kurzus");
            writer.println("\nKurzusok adatai:\n");
            System.out.println("\nKurzusok adatai:");
            for (int i = 0; i < kurzusok.getLength(); i++) {
                Node node = kurzusok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element kurzus = (Element) node;

                    // Kurzus adatainak kiírása
                    String kurzusID = kurzus.getElementsByTagName("KurzusID").item(0).getTextContent();
                    String idopont = kurzus.getElementsByTagName("Idopont").item(0).getTextContent();
                    String letszam = kurzus.getElementsByTagName("Letszam").item(0).getTextContent();

                    String output = String.format(
                        "Kurzus ID: %s\nIdőpont: %s\nLétszám: %s\n",
                        kurzusID, idopont, letszam
                    );

                    System.out.println(output);
                    writer.println(output);
                }
            }

            // Fájl lezárása
            writer.close();
            System.out.println("\nAdatok sikeresen mentve a 'DOMReadHK74CE_Output.txt' fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
