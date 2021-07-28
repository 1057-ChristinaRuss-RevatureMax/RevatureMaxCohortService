package dbconfig;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceClosure {
    public static void closeConnection(Connection conn) {
		if (conn == null) return;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static void closeStatement(Statement stmt) {
		if (stmt == null) return;
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void closeResultSet(ResultSet set) {
		if (set == null) return;
		try {
			set.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
