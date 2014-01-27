import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

/*  Unit Test for connections.
  	
  	Created by Michael Nowicki
  	January 25, 2014			*/

public class DBConnectionTest {

	@Test
	public void test() throws SQLException{

		DBConnection db = new DBConnection();
		assertTrue(db.connectToDB()); // Fails since DB address unknown ATM.
		assertTrue(db.disconnectDB());
	
	}

}
