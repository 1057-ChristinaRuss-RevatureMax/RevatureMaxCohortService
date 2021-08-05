package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
			"jdbc:postgresql://postgres.czxsou1mejft.us-east-2.rds.amazonaws.com:5432/postgres", 
			"postgres",
			"lacross24");
    }
}
