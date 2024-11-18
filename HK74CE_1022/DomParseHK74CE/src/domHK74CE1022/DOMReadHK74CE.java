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


public class DOMReadHK74CE {
	
	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException {
		
		File xmlFile = new File("hallgatoHK74CE.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("hallgato");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String hid = elem.getAttribute("id");
				
				Node node1 = elem.getElementsByTagName("keresztnev").item(0);
				String kname = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("vezeteknev").item(0);
				String vname = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("foglalkozas").item(0);
				String fname = node3.getTextContent();
				
				System.out.println("Hallgato id: " + hid);
				System.out.println("keresztnev: " + kname);
				System.out.println("vezeteknev: " + vname);
				System.out.println("foglalkozas: " + fname);
			}
		}
	}

}
