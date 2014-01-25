import static org.junit.Assert.*;

import org.junit.Test;

/*
 * 	Unit Test for connections.
 * 	Created by Michael Nowicki
 * 	January 25, 2014
 */

public class DBConnectionTest {

	@Test
	public void test(){

		DBConnect db = new DBConnect();
		assertTrue(db.connectToDB()); // Fails since DB address unknown ATM.
	
	}

}
