package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * This class is to setup the connection to the database
 */
public class RDSConnectionConfig {

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(
			System.getenv("db_url"), 
			System.getenv("db_username"),
			System.getenv("db_password"));


	}

}