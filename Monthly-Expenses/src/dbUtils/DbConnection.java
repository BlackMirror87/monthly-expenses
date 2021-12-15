package dbUtils;

import java.sql.*;

public class DbConnection {

	public static final String DB_NAME = "database_test.db";
	public static final String PATH ="C:\\Users\\sylwi\\Desktop\\Java\\src\\";
	public static final String CONNECTION_STRING = "jdbc:sqlite:" + PATH + DB_NAME;
	
	
	public static Connection getConnection() throws SQLException {
		
		Connection connection = DriverManager.getConnection(CONNECTION_STRING); 
	
		return connection;
		
	}
}
