package dbconfig;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.DriverManager;
//
//public class ConnectionConfig {
//
//    private Connection dbConnection;
//
//    public static Connection getConnection() throws SQLException {
//        Connection con = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            String hostname =  System.getenv("AWS_HOST_NAME");
//            String userName = System.getenv("DB_USER");
//            String password = System.getenv("DB_PASS");
//            String port = "5432";
//            String dbName = System.getenv("DB_NAME");
//
//
//            // Parameters for setting up the jdbcURL loaded in to connect to database currently set as postgres (anyone can access)
//            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
//            // jdbc setup for connection url to pass as a whole
//            con = DriverManager.getConnection(jdbcUrl);
//            return con;
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Connection getTestConnection() throws SQLException {
//        Connection con = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//            String hostname =  "localhost";
//            String port = "5432";
//            String userName = System.getenv("TEST_DB_USER");
//            String password = System.getenv("TEST_DB_PASS");
//            String dbName = System.getenv("DB_NAME");
//            // Parameters for setting up the jdbcURL loaded in to connect to database currently set as postgres (anyone can access)
//            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
//            // jdbc setup for connection url to pass as a whole
//            con = DriverManager.getConnection(jdbcUrl);
//            // store connection to database in a variable
//            return con;
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public int executeQuery(String query) throws ClassNotFoundException, SQLException {
//        return dbConnection.createStatement().executeUpdate(query);
//    }
//}