import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class CreateXMLFileTest2{

   public static void main(String argv[]) {

      try {
         DocumentBuilderFactory dbFactory =
         DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.newDocument();
         
         // root element
         Element rootElement = doc.createElement("class");
         doc.appendChild(rootElement);

         // student element
         Element student = doc.createElement("student");
         rootElement.appendChild(student);

         // setting attribute to element
         Attr attr = doc.createAttribute("rollno");
         attr.setValue("393");
         student.setAttributeNode(attr);

         // firstname element
         Element firstname = doc.createElement("firstname");
         firstname.appendChild(doc.createTextNode("Gena"));
         student.appendChild(firstname);
         
         // lastname element
         Element lastname = doc.createElement("lastname");
         lastname.appendChild(doc.createTextNode("Wu"));
         student.appendChild(lastname);
         
         // nickname element
         Element nickname = doc.createElement("nickname");
         nickname.appendChild(doc.createTextNode("GGena"));
         student.appendChild(nickname);
         
         // marks element
         Element marks = doc.createElement("marks");
         marks.appendChild(doc.createTextNode("95"));
         student.appendChild(marks);

         // write the content into xml file
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         //StreamResult result = new StreamResult(new File("C:\\cars.xml"));
         StreamResult result = new StreamResult(new File("test.xml"));
         transformer.transform(source, result);
         
         // Output to console for testing
         StreamResult consoleResult = new StreamResult(System.out);
         transformer.transform(source, consoleResult);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}