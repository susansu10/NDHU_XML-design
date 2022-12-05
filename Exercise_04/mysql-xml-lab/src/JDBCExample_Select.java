import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample_Select {
   static final String DB_URL = "jdbc:mysql://localhost/XMLDB";
   static final String USER = "root";
   static final String PASS = "0123456789";
   static final String QUERY = "SELECT id, first, last, mark, nick FROM class";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);
      ) {		      
         while(rs.next()){
            //Display values
        	 System.out.print("ID: " + rs.getInt("id"));
	         System.out.print(", Mark: " + rs.getInt("mark"));
	         System.out.print(", First: " + rs.getString("first"));
	         System.out.print(", Last: " + rs.getString("last"));
	         System.out.println(", Nick: " + rs.getString("nick"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}