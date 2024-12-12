package SaxHK74CE1203;

import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;



public class XsdValidation {
    public static void validateXml(File xsdFile, File xmlFile)throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            System.out.println("Successful validation!");

        } catch (SAXParseException e) {
            System.out.println("ERROR:: at Line: {" + e.getLineNumber() + "} Column: {" + e.getColumnNumber()
                    + "} Message: {" + e.getMessage() + "}");
        }
    }

    public static void main(String[] args)throws Exception {
        File xsdFile = new File("hk74ce_kurzusfelvetel.xsd");
        File xmlFile = new File("hk74ce_kurzusfelvetel.xml");
        validateXml(xsdFile, xmlFile);
    }
}