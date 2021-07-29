package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");

            String hostname =  "localhost";
            String port = "5432";
            String userName = "#######";
            String password = "########";
            String dbName = "#########";

            // Parameters for setting up the jdbcURL loaded in to connect to database currently set as postgres (anyone can access)
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
            // jdbc setup for connection url to pass as a whole
            con = DriverManager.getConnection(jdbcUrl);
            // store connection to database in a variable
            System.out.println("Connection successful");
            // if connection passes we print out "connection successful" can be removed in the future (still in for testing cases)
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
