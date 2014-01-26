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
	
	public void userQuery(ArrayList<? extends Object> a) throws SQLException{
		
		String query = "select AI_OUT from " + dbc.getDBName() +
				".something where USER_IN = " + a.get(0);
		try {
			stmt = dbc.getConn().createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String AI_OUT = rs.getString("AI_OUT");
	        }
	        
	    } catch (SQLException e ) {
	        e.printStackTrace();
	    } finally {
	    	if (stmt != null){ 
	        	stmt.close(); 
	    	}
	    }
		
	}
	
	

}
