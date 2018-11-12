package data.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		if (connection != null) {
			return connection;
		} else {
			try {
							
				String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				String url = "jdbc:sqlserver://localhost:1433;Database=tp2java"; //tp2java
				String user = "sa";
				String password = "123456";
				
				Class.forName(driver); //Verifica se o driver está no CLASSPATH
				
				connection = DriverManager.getConnection(url, user, password);
				
				connection.setAutoCommit(true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} //if
		
		return connection;
		
	} //getConnection

} //DbUtil
