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
			System.getenv("db_name"),
			System.getenv("db_name"),
			System.getenv("db_password"));


	}

}