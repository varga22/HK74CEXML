package xpathhk74ce1119;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class xPathHK74CE {
    public static void main(String[] args) {
        try {
            // XML dokumentum beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("studentHK74CE.xml");

            // XPath objektum létrehozása
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            // 1. Válassza ki az összes student element, amely a class gyermekei.
            XPathExpression expression1 = xPath.compile("/class/student");
            NodeList students1 = (NodeList) expression1.evaluate(doc, XPathConstants.NODESET);
            System.out.println("1. Az összes student elem:");
            for (int i = 0; i < students1.getLength(); i++) {
                System.out.println(students1.item(i).getTextContent());
            }

            // 2. Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"!
            XPathExpression expression2 = xPath.compile("/class/student[@id='2']");
            NodeList students2 = (NodeList) expression2.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n2. id=2 student elem:");
            for (int i = 0; i < students2.getLength(); i++) {
                System.out.println(students2.item(i).getTextContent());
            }

            // 3. Kiválasztja az összes student elemet, függetlenül attól hogy hol vannak a dokumentumban!
            XPathExpression expression3 = xPath.compile("//student");
            NodeList students3 = (NodeList) expression3.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n3. Az összes student elem a dokumentumban:");
            for (int i = 0; i < students3.getLength(); i++) {
                System.out.println(students3.item(i).getTextContent());
            }

            // 4. Válassza ki a második student element, amely a class root element gyermeke!
            XPathExpression expression4 = xPath.compile("/class/student[2]");
            String student4 = (String) expression4.evaluate(doc, XPathConstants.STRING);
            System.out.println("\n4. A második student elem:");
            System.out.println(student4);

            // 5. Válassza ki az utolsó student element, amely a class root element gyermeke!
            XPathExpression expression5 = xPath.compile("/class/student[last()]");
            String student5 = (String) expression5.evaluate(doc, XPathConstants.STRING);
            System.out.println("\n5. Az utolsó student elem:");
            System.out.println(student5);

            // 6. Válassza ki az utolsó előtti student element, amely a class root element gyermeke!
            XPathExpression expression6 = xPath.compile("/class/student[last()-1]");
            String student6 = (String) expression6.evaluate(doc, XPathConstants.STRING);
            System.out.println("\n6. Az utolsó előtti student elem:");
            System.out.println(student6);

            // 7. Válassza ki az első két student element, amely a root element gyermekei!
            XPathExpression expression7 = xPath.compile("/class/student[position() <= 2]");
            NodeList students7 = (NodeList) expression7.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n7. Az első két student elem:");
            for (int i = 0; i < students7.getLength(); i++) {
                System.out.println(students7.item(i).getTextContent());
            }

            // 8. Válassza ki a class root element összes gyermek elemét!
            XPathExpression expression8 = xPath.compile("/class/*");
            NodeList children8 = (NodeList) expression8.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n8. A class összes gyermeke:");
            for (int i = 0; i < children8.getLength(); i++) {
                System.out.println(children8.item(i).getTextContent());
            }

            // 9. Válassza ki az összes student elemet, amely rendelkezik legalább, egy bármilyen attribútummal!
            XPathExpression expression9 = xPath.compile("//student[@*]");
            NodeList students9 = (NodeList) expression9.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n9. Az attribútummal rendelkező student elemek:");
            for (int i = 0; i < students9.getLength(); i++) {
                System.out.println(students9.item(i).getTextContent());
            }

            // 10. Válassza ki a dokumentum összes elemét!
            XPathExpression expression10 = xPath.compile("//*");
            NodeList elements10 = (NodeList) expression10.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n10. Az összes elem a dokumentumban:");
            for (int i = 0; i < elements10.getLength(); i++) {
                System.out.println(elements10.item(i).getTextContent());
            }

         // 11. Válassza ki a class root element összes student elemét, amelynél a kor > 20!
            XPathExpression expression11 = xPath.compile("/class/student[number(kor) > 20]");
            NodeList students11 = (NodeList) expression11.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n11. Azok a student elemek, ahol a kor > 20:");
            for (int i = 0; i < students11.getLength(); i++) {
                System.out.println(students11.item(i).getTextContent());
            }

         // 12. Válassza ki az összes student elem összes keresztnev or vezeteknev csomópontot!
            XPathExpression expression12 = xPath.compile("/class/student/keresztnev | /class/student/vezeteknev");
            NodeList nodes12 = (NodeList) expression12.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n12. Az összes keresztnev vagy vezeteknev csomópont:");
            for (int i = 0; i < nodes12.getLength(); i++) {
                System.out.println(nodes12.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
