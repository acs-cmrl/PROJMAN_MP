package database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import utils.Debug;

public class DatabaseConnectionPool {

	// TODO read from properties file the connection details
	public final static String url = "jdbc:mysql://127.0.0.1:3307/malateportfolio?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public final static String username = "root";
	public final static String password = "3rqVMBRcg3-dVnqYt0N9";
	public final static String driver = "com.mysql.cj.jdbc.Driver";
	
	//an instance of itself
	private static DatabaseConnectionPool instance = null;
	private static BasicDataSource basicDataSource;
	//private Constructor
	private DatabaseConnectionPool(){
		//we want to initialize the driver which will connect to db
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
	}
	
	//method getInstance
	public static DatabaseConnectionPool getInstance(){
		if(instance == null)
			instance = new DatabaseConnectionPool();
		return instance;
	}
	
	public Connection getConnection(){
		try {
			return basicDataSource.getConnection();
		} catch (SQLException e) {
			Debug.error("DatabaseConnectionPool.getConnection", e.getMessage());
		}
		return null;
	}
}
