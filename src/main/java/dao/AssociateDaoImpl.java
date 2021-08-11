package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

public class AssociateDaoImpl implements AssociateDao {

    public AssociateDaoImpl() {

    }

    public ArrayList<String> getAllSalesForceId(){
        ArrayList<String> ids = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select salesforceId from associate";
            assert conn != null;
            stmt = conn.prepareStatement(SQL);
            set = stmt.executeQuery();
            while(set.next()) {
                ids.add(set.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return ids;
        }

    }

    @Override
    public void createAssociate(String salesforceId, String firstName, String lastName, String email, String batchID, String pass_word) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into associate values(?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
            assert conn != null;
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, batchID);
            stmt.setString(6, pass_word);
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
            assert conn != null;
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            set = stmt.executeQuery();
            while(set.next()) {
                associate.setSalesforceId(set.getString(1));
                associate.setFirstname(set.getString(2));
                associate.setLastname(set.getString(3));
                associate.setEmail(set.getString(4));
                associate.setBatchID(set.getString(5));
                associate.setPassword(set.getString(6));
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
            assert conn != null;
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            set = stmt.executeQuery();
            while(set.next()) {
                associate.setSalesforceId(set.getString(1));
                associate.setFirstname(set.getString(2));
                associate.setLastname(set.getString(3));
                associate.setEmail(set.getString(4));
                associate.setBatchID(set.getString(5));
                associate.setPassword(set.getString(6));
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
    public String getBatchID(String email){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        String result = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Select batchid from associate where email = ?";
            assert conn != null;
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            set = stmt.executeQuery();

            while(set.next()) {
                result = set.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return result;
        }
    }

    public void updateAssociateFirstname(String salesforceId, String firstname) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update associate set firstname = ? where salesforceId = ?";
            assert conn != null;
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
            assert conn != null;
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
            assert conn != null;
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
