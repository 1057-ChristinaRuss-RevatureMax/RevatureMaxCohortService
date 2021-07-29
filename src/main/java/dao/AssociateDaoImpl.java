package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssociateDaoImpl implements AssociateDao {

    public AssociateDaoImpl() {

    }

    @Override
    public void createOne(String salesforceId, String firstName, String lastName, String email, String pass_word) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into associates values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, pass_word);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
}