package SaxHK74CE1203;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxHK74CE {

    public static void main(String[] args) {
        try {
            // Initialize SAX parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Create a custom handler
            DefaultHandler handler = new DefaultHandler() {

                boolean isHallgato = false;
                boolean isKurzusNev = false;
                boolean isKredit = false;
                boolean isIdopont = false;
                boolean isHely = false;
                boolean isOktato = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("hallgato")) {
                        System.out.println("\nHallgató adatai:");
                        isHallgato = true;
                    }
                    if (qName.equalsIgnoreCase("kurzus")) {
                        System.out.println("\nKurzus ID: " + attributes.getValue("id"));
                        if (attributes.getValue("jovahagyas") != null) {
                            System.out.println("Jóváhagyás: " + attributes.getValue("jovahagyas"));
                        }
                        if (attributes.getValue("nyelv") != null) {
                            System.out.println("Nyelv: " + attributes.getValue("nyelv"));
                        }
                    }
                    if (qName.equalsIgnoreCase("kurzusnev")) {
                        isKurzusNev = true;
                    }
                    if (qName.equalsIgnoreCase("kredit")) {
                        isKredit = true;
                    }
                    if (qName.equalsIgnoreCase("hely")) {
                        isHely = true;
                    }
                    if (qName.equalsIgnoreCase("idopont")) {
                        isIdopont = true;
                    }
                    if (qName.equalsIgnoreCase("oktato")) {
                        isOktato = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isHallgato) {
                        System.out.println(new String(ch, start, length));
                        isHallgato = false;
                    }
                    if (isKurzusNev) {
                        System.out.println("Kurzusnév: " + new String(ch, start, length));
                        isKurzusNev = false;
                    }
                    if (isKredit) {
                        System.out.println("Kredit: " + new String(ch, start, length));
                        isKredit = false;
                    }
                    if (isHely) {
                        System.out.println("Hely: " + new String(ch, start, length));
                        isHely = false;
                    }
                    if (isIdopont) {
                        System.out.println("Időpont: " + new String(ch, start, length));
                        isIdopont = false;
                    }
                    if (isOktato) {
                        System.out.println("Oktató: " + new String(ch, start, length));
                        isOktato = false;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("hallgato")) {
                        System.out.println("Hallgató adatai vége.");
                    }
                }
            };

            // Parse the XML file
            File inputFile = new File("hk74ce_kurzusfelvetel.xml");
            saxParser.parse(inputFile, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
