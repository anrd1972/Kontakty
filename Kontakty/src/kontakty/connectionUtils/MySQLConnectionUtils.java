package kontakty.connectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtils {

	public static Connection dajPolaczenieMySQL() throws ClassNotFoundException, SQLException {

		String hostname = "localhost";
		String dbName = "kontakty";
		String username = "root";
		String rootPassword = "start00";

		return dajPolaczenieMySQL(hostname, dbName, username, rootPassword);
	}

	public static Connection dajPolaczenieMySQL(String hostname, String dbName, String username, String rootPassword) {

		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName;

			conn = DriverManager.getConnection(connectionURL, username, rootPassword);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

}
