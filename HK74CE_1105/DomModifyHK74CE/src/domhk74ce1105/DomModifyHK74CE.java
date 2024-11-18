package domhk74ce1105;

import java.io.File;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomModifyHK74CE {

	public static void main(String[] argv) {
		// TODO Auto-generated method stub
		try {
			File inputFile = new File("UjhallgatoHK74CE.xml");

			DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docfactory.newDocumentBuilder();
            
            Document doc = docBuilder.parse(inputFile);
            System.out.println("XML fájl beolvasva sikeresen.");
            
            doc.getDocumentElement().normalize();
            
            Node hallgat = doc.getElementsByTagName("hallgato").item(0);
            
            //hallgat attributumának módosítása
            NamedNodeMap attr = hallgat.getAttributes();
            Node nodeAttr = attr.getNamedItem("id");
            nodeAttr.setTextContent("01");
            
            NodeList list = hallgat.getChildNodes();
            
            for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					if ("keresztnev".equals(element.getNodeName())) {
						if ("Pál".equals(element.getTextContent())) {
							element.setTextContent("Olivia");
						}
					}
					
					if ("vezeteknev".equals(element.getNodeName())) {
						if ("Kiss".equals(element.getTextContent())) {
							element.setTextContent("Nagyfejeo");
						}
					}
					
					
				}
			}
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            
            System.out.println("---Módosított fájlt--");
            System.out.println();
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
