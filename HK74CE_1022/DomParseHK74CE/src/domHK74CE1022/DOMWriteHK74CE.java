package domHK74CE1022;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class DOMWriteHK74CE {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException{
		// TODO Auto-generated method stub
		
		//DBF példányosítása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//Új dokumentum
		Document doc = builder.newDocument();
		
		//Gyökérelem
		Element root = doc.createElementNS("DOMHK74CE", "hallgatok");
		doc.appendChild(root);
		
		//A gyökérelemhez 3gyerekelemet fűzünk.
		root.appendChild(createUser(doc, "1", "Péter", "Nagy", "Web Developer"));
		root.appendChild(createUser(doc, "2", "Piroska", "Vigh", "Java Programozo"));
		root.appendChild(createUser(doc, "3", "Kiss", "Ferenc", "Associate Professor"));
		
		//A java DOM a Transformer objektumot használja az XML fájl létrehozásához
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		//Dokumentum kódolása
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		//Bemeneti forrásfájl létrehozása
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("hallgatok1HK74CE.xml");
		//Írjunk egy konzolba és egy fájlba
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	//CreateUser
	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		Element user = doc.createElement("hallgato");
		
		//setAttribute() metódus
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "keresztnev", firstName));
		user.appendChild(createUserElement(doc, "vezeteknev", lastName));
		user.appendChild(createUserElement(doc, "foglalkozas", profession));
		
		return user;
	}

	private static Node createUserElement(Document doc, String name, String value) {
		// TODO Auto-generated method stub
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}

}
