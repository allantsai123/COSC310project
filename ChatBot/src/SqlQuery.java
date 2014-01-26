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
	
	// Constructor, initialize connection to database.
	public SqlQuery(){
		
		stmt = null;
		dbc = new DBConnection();
		dbc.connectToDB();
		
	}
	
	public void userQuery(ArrayList<? extends Object> a){
		
	}
	
	

}
