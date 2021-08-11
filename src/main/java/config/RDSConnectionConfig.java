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
				"jdbc:postgresql://postgres.czxsou1mejft.us-east-2.rds.amazonaws.com:5432/postgres",
				"postgres",
				"lacross24");
	}
}