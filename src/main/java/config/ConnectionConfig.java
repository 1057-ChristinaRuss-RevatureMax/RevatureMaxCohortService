package config;

import os;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
			System.getenv("URL"), 
			System.getenv("DB_NAME"),
			System.getenv("DB_PASS");
    }
}
