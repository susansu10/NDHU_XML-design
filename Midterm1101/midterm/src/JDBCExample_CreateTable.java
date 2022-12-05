import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample_CreateTable {
   static final String DB_URL = "jdbc:mysql://localhost:3306/midterm";
   static final String USER = "root";
   static final String PASS = "0123456789";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
          String sql = "CREATE TABLE score_data" +
                   "(student_id VARCHAR(5), " +
                   " xml_score INTEGER, " + 
                   " data_structure_score INTEGER, " + 
                   " alogrithm_score INTEGER, " + 
                   " network_score INTEGER, " + 
                   " PRIMARY KEY ( student_id ))"; 

         stmt.executeUpdate(sql);
         System.out.println("Created table in given database...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}