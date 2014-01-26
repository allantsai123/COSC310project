import java.sql.*;

import com.mysql.jdbc.Connection;

/* This class is used to initialize a connection to the DB of 'canned' responses.
   Internal methods for interacting with the database.
   
   Create January 25, 2014
   Michael Nowicki 																*/

public class DBConnection {

	private Connection conn;
	
	// Driver and DB address
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  

	// Change '//localhost/STUDENTS' to proper DB address when known.
	private final String DB_URL = "jdbc:mysql://localhost/mysqlsampledatabase";

	
//	Don't know if we need log in info.
	
	//Database credentials
//	static final String USER = "username";
//	static final String PASS = "password";

	// Initialize connection class
	public DBConnection(){
		
		conn = null;
		
	}
	
	/* Boolean method, forms connection to the database. Only returns true
	   if connection is established to the database specified by the DB_URL. */
	public boolean connectToDB(){
		try{
			// Ensure correct driver
			Class.forName(JDBC_DRIVER);
			// Establish Connection to database
			conn = (Connection) DriverManager.getConnection(DB_URL);
			return true;
			
		} catch (SQLException se){
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Returns the database connection.
	public Connection getConn(){
		return this.conn;
	}
	
	// Closes connection to database when program is finished.
	public boolean disconnectDB(Connection c){
		
		
		this.conn = c;
		
		try {
			conn.close();
			if(conn.isClosed()){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
}
