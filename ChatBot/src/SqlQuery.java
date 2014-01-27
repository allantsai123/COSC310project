import java.sql.*;
import java.util.ArrayList;


/* 	This class is used by the system to interface with the database,
 	and form SQL queries based on the user input. Will return a
 	response from the database for the user.
 	
 	Created by Michael Nowicki
 	January 25, 2014													*/

public class SqlQuery<E> {
	
	private DBConnection dbc;
	private Statement stmt;
	private ResultSet rs;
	
	// Constructor, initialize connection to database.
	public SqlQuery(){
		
		stmt = null;
		rs = null;
		dbc = new DBConnection();
		dbc.connectToDB();
		
	}
	
	// Method takes user input in a generic array list to be used
	// for an SQL query.
	public String userQuery(ArrayList<? extends Object> a) throws SQLException{
		
		String query = "select AI_OUT from " + dbc.getDBName() +
				".something where USER_IN = " + a.get(0);
		
		String AI_OUT = null;
		
		try {
			stmt = dbc.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        // Create and execute a simple query. Selected data/columns
	        // can be changed as the DB structure is known. 
	        
	        // If we want multiple responses these can be stored in an array
	        // and maybe use an RNG to pick one from the array to return.
	        while (rs.next()) {
	            AI_OUT = rs.getString("AI_OUT");
	        }
	    } catch (SQLException e ) {
	        e.printStackTrace();
	        return null;
	    } finally {
	    	if (stmt != null){ 
	        	stmt.close(); 
	    	} 	
	    }
		
		return AI_OUT;
		
	}
	
	

}
