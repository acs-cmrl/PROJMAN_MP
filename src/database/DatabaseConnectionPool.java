package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.Debug;

public class DatabaseConnectionPool {

	// TODO read from properties file the connection details
	public final static String url = "jdbc:mysql://127.0.0.1:3307/malateportfolio?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public final static String username = "root";
	public final static String password = "3rqVMBRcg3-dVnqYt0N9";
	public final static String driver = "com.mysql.cj.jdbc.Driver";
	
	//an instance of itself
	private static DatabaseConnectionPool instance = null;
	//private Constructor
	private DatabaseConnectionPool(){
		//we want to initialize the driver which will connect to db
	}
	
	//method getInstance
	public static DatabaseConnectionPool getInstance(){
		if(instance == null)
			instance = new DatabaseConnectionPool();
		return instance;
	}
	
	public Connection getConnection(){

			try{
				Class.forName(driver);
				return DriverManager.getConnection(url,username,password);
			} catch (SQLException | ClassNotFoundException e) {
				Debug.error("DatabaseConnectionPool.getConnection", e.getMessage());
			}
			return null;
	}
}
