package data.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbUtil {

	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		if (connection != null) {
			return connection;
		} else {
			try {
							

				//TODO trocar
				//String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				//String url = "jdbc:sqlserver://CTS08398559;databaseName=parkWay;integratedSecurity=true"; //tp2java
				
				String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			    String url = "jdbc:sqlserver://localhost:1433;Database=parkWay"; //tp2java
				String user = "sa";
				String password = "123456";
								
				Class.forName(driver); //Verifica se o driver está no CLASSPATH
				
				connection = DriverManager.getConnection(url, user, password);
				//connection = DriverManager.getConnection(url);
				
				connection.setAutoCommit(true);
				
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao se conectar com o servidor, entre em contato com o suporte!");
			}
		} //if
		
		return connection;
		
	} //getConnection

} //DbUtil
