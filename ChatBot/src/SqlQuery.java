import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class SqlQuery {

	Connection myConnection;
	
	// Initialize connection to database
	public SqlQuery(){
		
		// Address of MySQL server
		String mySqlUrl = "jdbc:mysql://localhost:3306/mysql";
	    try {
	        myConnection = (Connection) DriverManager.getConnection(mySqlUrl);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	
	
}
