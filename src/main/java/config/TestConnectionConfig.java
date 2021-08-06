package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class TestConnectionConfig{
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "localhost",
                "postgres",
                "postgres");
    }
}
