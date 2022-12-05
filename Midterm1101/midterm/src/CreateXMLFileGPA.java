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

public class CreateXMLFileGPA
{
	static double GPA(int score) 
	{
		double num;
		if(score >= 90 && score <= 100)
		{
			num = 4.5;
			return (num);
		}
		else if(score >= 85 && score <= 89)
		{
			num = 4.0;
			return (num);
		}
		else if(score >= 80 && score <= 84)
		{
			num = 3.7;
			return (num);
		}
		else if(score >= 77 && score <= 79)
		{
			num = 3.3;
			return (num);
		}
		else if(score >= 73 && score <= 76) 
		{
			num = 3.0;
			return (num);
		}
		else if(score >= 70 && score <= 72) 
		{
			num = 2.7;
			return (num);
		}
		else if(score >= 67 && score <= 69) 
		{
			num = 2.5;
			return (num);
		}
		else if(score >= 63 && score <= 66) 
		{
			num = 2.3;
			return (num);
		}
		else if(score >= 60 && score <= 62) 
		{
			num = 2.0;
			return (num);
		}
		else if(score >= 50 && score <= 59) 
		{
			num = 1.0;
			return (num);
		}
		else
		{
			num = 0.0;
			return (num);
		}
	}
	static final String DB_URL = "jdbc:mysql://localhost/midterm";
	static final String USER = "root";
	static final String PASS = "0123456789";
	static final String QUERY = "SELECT student_id, xml_score, data_structure_score, alogrithm_score, network_score FROM score_data";
	public static void main(String argv[]) throws ParserConfigurationException 
	{
		  String id;
		  Double gpa_xml,gpa_datas,gpa_alg,gpa_net;
   	      Integer xml,datas,algo,netw;
   	      
		  try 
		  {
		      DocumentBuilderFactory dbFactory =
		      DocumentBuilderFactory.newInstance();
		      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		      Document doc = dBuilder.newDocument();
		         
		      // root element
		      Element rootElement = doc.createElement("score_data");
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
		    			  gpa_xml = GPA(xml); 
		    			  System.out.print(", Data_Structure Class : " + rs.getInt("data_structure_score"));
		    			  datas = rs.getInt("data_structure_score");
		    			  gpa_datas = GPA(datas);
		    			  System.out.print(", Algorithm Class : " + rs.getInt("alogrithm_score"));
		    			  algo = rs.getInt("alogrithm_score");
		    			  gpa_alg = GPA(algo);
		    			  System.out.println(", Network Class : " + rs.getInt("network_score"));
		    			  netw = rs.getInt("network_score");
		    			  gpa_net = GPA(netw);
		    			  
		    			  // student element
		    			  Element student = doc.createElement("student");
		    			  rootElement.appendChild(student);

		    			  // setting attribute to element
		    			  Attr attr = doc.createAttribute("student_id");
		    			  attr.setValue(id);
		    			  student.setAttributeNode(attr);

		    			  // XML_C element
		    			  Element XML_C = doc.createElement("xml_class");
		    			  Attr xml_c = doc.createAttribute("gpa");
		    			  xml_c.setValue(Double.toString(gpa_xml));
		    			  XML_C.setAttributeNode(xml_c);
		    			  XML_C.appendChild(doc.createTextNode(Integer.toString(xml)));
		    			  student.appendChild(XML_C);
		          
		    			  // DATAS_C element
		    			  Element DATAS_C = doc.createElement("data_structure");
		    			  Attr datas_c = doc.createAttribute("gpa");
		    			  datas_c.setValue(Double.toString(gpa_datas));
		    			  DATAS_C.setAttributeNode(datas_c);
		    			  DATAS_C.appendChild(doc.createTextNode(Integer.toString(datas)));
		    			  student.appendChild(DATAS_C);
		          
		    			  // ALG_C element
		    			  Element ALG_C = doc.createElement("algorithm");
		    			  Attr alg_c = doc.createAttribute("gpa");
		    			  alg_c.setValue(Double.toString(gpa_alg));
		    			  ALG_C.setAttributeNode(alg_c);
		    			  ALG_C.appendChild(doc.createTextNode(Integer.toString(algo)));
		    			  student.appendChild(ALG_C);
		          
		    			  // NETW_C element
		    			  Element NETW_C = doc.createElement("network");
		    			  Attr netw_c = doc.createAttribute("gpa");
		    			  netw_c.setValue(Double.toString(gpa_net));
		    			  NETW_C.setAttributeNode(netw_c);
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
			  StreamResult result = new StreamResult(new File("output.xml"));
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