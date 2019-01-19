package web.app.eng.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionFactory {
	
	public static final String database = "jdbc:mysql://localhost:3306/unswbook";
	public static final Properties properties = new Properties();
	
	public static Connection getConnection() {
		Connection connection = null;
		
		properties.setProperty("user", "root");
		properties.setProperty("password", "root");
		properties.setProperty("useSSL", "false");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(database, properties);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
}