import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DomParserDemo 
{
	static final String DB_URL = "jdbc:mysql://localhost/midterm";
	static final String USER = "root";
	static final String PASS = "0123456789";

   public static void main(String[] args) 
   {
      try 
      {
    	 String id;
    	 Integer xml,datas,algo,netw;
    	 
         File inputFile = new File("score_data.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("student");
         System.out.println("----------------------------");
         
         
         for (int temp = 0; temp < nList.getLength(); temp++) 
         {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) 
            {
               Element eElement = (Element) nNode;
               
               System.out.println("Student ID : " 
                  + eElement.getAttribute("student_id"));
               id = eElement.getAttribute("student_id");
               
               System.out.println("XML Class: "
               + eElement
               .getElementsByTagName("xml_class")
               .item(0)
               .getTextContent());
               xml = Integer.parseInt(eElement.getElementsByTagName("xml_class").item(0).getTextContent());
               
               System.out.println("Data_Structure Class : "
               + eElement
               .getElementsByTagName("data_structure")
               .item(0)
               .getTextContent());
               datas = Integer.parseInt(eElement.getElementsByTagName("data_structure").item(0).getTextContent());
               
               System.out.println("Algorithm Class : "
               + eElement
               .getElementsByTagName("algorithm")
               .item(0)
               .getTextContent());
               algo = Integer.parseInt(eElement.getElementsByTagName("algorithm").item(0).getTextContent());
               
               System.out.println("Network Class : "
               + eElement
               .getElementsByTagName("network")
               .item(0)
               .getTextContent());
               netw = Integer.parseInt(eElement.getElementsByTagName("network").item(0).getTextContent());
               
               try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            	         Statement stmt = conn.createStatement();) 
               {
            	   String  sql_insert = "INSERT INTO score_data(student_id,xml_score,data_structure_score,alogrithm_score,network_score) VALUES (?,?,?,?,?)";
            	   PreparedStatement preparedStatement = conn.prepareStatement(sql_insert);
            	   preparedStatement.setString(1, id);
            	   preparedStatement.setInt(2, xml);
            	   preparedStatement.setInt(3, datas);
            	   preparedStatement.setInt(4, algo);
            	   preparedStatement.setInt(5, netw);
            	   System.out.println(preparedStatement);
            	   preparedStatement.executeUpdate();
               }
               catch (SQLException e) 
               {
                   e.printStackTrace();
               }
            }
         }
      } 
      catch (Exception e) 
      {
         e.printStackTrace();
      }
   }
}
