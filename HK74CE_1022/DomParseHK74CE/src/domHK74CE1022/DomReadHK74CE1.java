package domHK74CE1022;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomReadHK74CE1 {
	
	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException {
		
		File xmlFile = new File("OrarendHK74CE.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("ora");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String hid = elem.getAttribute("id");
				
				Node node1 = elem.getElementsByTagName("targy").item(0);
				String targy = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("idopont").item(0);
				String idopont = node2.getTextContent();
			
				Node node7 = elem.getElementsByTagName("helyszin").item(0);
				String helyszin = node7.getTextContent();
				
				Node node8 = elem.getElementsByTagName("oktato").item(0);
				String oktato = node8.getTextContent();
				
				System.out.println("hallgato id: " + hid);
				System.out.println("targy: " + targy);
				System.out.println("idopont: " + idopont);
				System.out.println("helyszin: " + helyszin);
				System.out.println("oktato: " + oktato);
			}
		}
	}

}
