package kontakty.connectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnectionUtils {
	
	public static Connection getMyConnection() throws ClassNotFoundException, SQLException {
		
		return MySQLConnectionUtils.dajPolaczenieMySQL();
		
	}
	
	public static void closeMyConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			//nic nie rob
		}
	}
	
	public static void myRollback(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
			//nic nie rob
		}
	}

}
