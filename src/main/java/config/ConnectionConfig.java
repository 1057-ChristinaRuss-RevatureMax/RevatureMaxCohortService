package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(
				System.getenv("db_url"), 
				System.getenv("db_username"),
				System.getenv("db_password"));
    }
}
