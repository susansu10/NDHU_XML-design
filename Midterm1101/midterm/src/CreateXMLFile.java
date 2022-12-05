import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateXMLFile
{
	static final String DB_URL = "jdbc:mysql://localhost/midterm";
	static final String USER = "root";
	static final String PASS = "0123456789";
	static final String QUERY = "SELECT student_id, xml_score, data_structure_score, alogrithm_score, network_score FROM score_data";
	public static void main(String argv[]) throws ParserConfigurationException 
	{
		  String id;
   	      Integer xml,datas,algo,netw;
   	      
		  try 
		  {
		      DocumentBuilderFactory dbFactory =
		      DocumentBuilderFactory.newInstance();
		      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		      Document doc = dBuilder.newDocument();
		         
		      // root element
		      Element rootElement = doc.createElement("class_score");
		      doc.appendChild(rootElement);
		      
		      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		 	         Statement stmt = conn.createStatement();
		 	         ResultSet rs = stmt.executeQuery(QUERY);
		 	      ) 
		 	  {
		    	  while(rs.next())
		    	  {
		    		  try
		    		  {
		    			  //Display values
		    			  System.out.print("Student ID : " + rs.getString("student_id"));
		    			  id = rs.getString("student_id");
		    			  System.out.print(", XML Class: " + rs.getInt("xml_score"));
		    			  xml = rs.getInt("xml_score");
		    			  System.out.print(", Data_Structure Class : " + rs.getInt("data_structure_score"));
		    			  datas = rs.getInt("data_structure_score");
		    			  System.out.print(", Algorithm Class : " + rs.getInt("alogrithm_score"));
		    			  algo = rs.getInt("alogrithm_score");
		    			  System.out.println(", Network Class : " + rs.getInt("network_score"));
		    			  netw = rs.getInt("network_score");
		 	            	
		    			  // student element
		    			  Element student = doc.createElement("student");
		    			  rootElement.appendChild(student);

		    			  // setting attribute to element
		    			  Attr attr = doc.createAttribute("ID");
		    			  attr.setValue(id);
		    			  student.setAttributeNode(attr);

		    			  // XML_C element
		    			  Element XML_C = doc.createElement("XML_C");
		    			  XML_C.appendChild(doc.createTextNode(Integer.toString(xml)));
		    			  student.appendChild(XML_C);
		          
		    			  // DATAS_C element
		    			  Element DATAS_C = doc.createElement("DATAS_C");
		    			  DATAS_C.appendChild(doc.createTextNode(Integer.toString(datas)));
		    			  student.appendChild(DATAS_C);
		          
		    			  // ALG_C element
		    			  Element ALG_C = doc.createElement("ALG_C");
		    			  ALG_C.appendChild(doc.createTextNode(Integer.toString(algo)));
		    			  student.appendChild(ALG_C);
		          
		    			  // NETW_C element
		    			  Element NETW_C = doc.createElement("NETW_C");
		    			  NETW_C.appendChild(doc.createTextNode(Integer.toString(netw)));
		    			  student.appendChild(NETW_C);
		    		  }
		    		  catch (Exception e)
		    		  {
		 	            	e.printStackTrace();
		 	          }
		 	      }
		 	  }
		      catch (SQLException e)
		      {
		 	         e.printStackTrace();
		 	  }
		      
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
		  } 
		  catch (Exception e) 
		  {
		         e.printStackTrace();
		  }
	}
}