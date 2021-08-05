package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssociateDaoImpl implements AssociateDao {

    public AssociateDaoImpl() {

    }

    @Override
    public void createAssociate(String salesforceId, String firstName, String lastName, String email, String pass_word) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into associate values(?, ?, ?, ?, ?)";
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

    @Override
    public Associate getAssociateBySalesforce(String salesforceId) {
        Associate associate = new Associate();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select * from associate where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            set = stmt.executeQuery();
            while(set.next()) {
                associate.setSalesforceId(set.getString(1));
                associate.setFirstname(set.getString(2));
                associate.setLastname(set.getString(3));
                associate.setEmail(set.getString(4));
                associate.setPassword(set.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return associate;
        }
    }

    @Override
    public Associate getAssociateByEmail(String email) {
        Associate associate = new Associate();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select * from associate where email = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            set = stmt.executeQuery();
            while(set.next()) {
                associate.setSalesforceId(set.getString(1));
                associate.setFirstname(set.getString(2));
                associate.setLastname(set.getString(3));
                associate.setEmail(set.getString(4));
                associate.setPassword(set.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return associate;
        }
    }

    public void updateAssociateFirstname(String salesforceId, String firstname) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update associate set firstname = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, firstname);
            stmt.setString(2, salesforceId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
    public void updateAssociateLastname(String salesforceId, String lastname){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update associate set lastname = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lastname);
            stmt.setString(2, salesforceId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
    public void updateAssociateEmail(String salesforceId, String email){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update associate set email = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            stmt.setString(2, salesforceId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
}