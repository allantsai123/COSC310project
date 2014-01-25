import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

/* 
   This class is used to initialize a connection to the DB of 'canned' responses.
   Internal methods for interacting with the database.
   Create January 25, 2014
   Michael Nowicki 
 																				*/

public class DBConnect {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rslt;
	
	// Driver and DB address
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private final String DB_URL = "jdbc:mysql://localhost/STUDENTS";

	
//	Don't know if we need log in info.
	
	//Database credentials
//	static final String USER = "username";
//	static final String PASS = "password";

	// Initialize connection class
	public DBConnect(){
		
		conn = null;
		
	}
	
	// Boolean method, forms connection to the database. Only returns true
	// if connection is established to the database specified by the DB_URL.
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
	
}
