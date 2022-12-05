import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample_Select {
   static final String DB_URL = "jdbc:mysql://localhost/midterm";
   static final String USER = "root";
   static final String PASS = "0123456789";
   static final String QUERY = "SELECT student_id, xml_score, data_structure_score, alogrithm_score, network_score FROM score_data";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);
      ) {		      
         while(rs.next()){
            //Display values
        	 System.out.print("Student ID : " + rs.getString("student_id"));
	         System.out.print(", XML Class: " + rs.getInt("xml_score"));
	         System.out.print(", Data_Structure Class : " + rs.getInt("data_structure_score"));
	         System.out.print(", Algorithm Class : " + rs.getInt("alogrithm_score"));
	         System.out.println(", Network Class : " + rs.getInt("network_score"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}