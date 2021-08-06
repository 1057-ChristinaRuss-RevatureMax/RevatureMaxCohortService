package dao;

import config.ConnectionConfig;
import config.RDSConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDaoImpl {
    public void createEmployee(int salesforceId, String firstName, String lastName, String email, String pass_word) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into employee values(?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, salesforceId);
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

    public void updateAssociateFirstname(int salesforceId, String firstname) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update employee set firstname = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, firstname);
            stmt.setInt(2, salesforceId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
    public void updateAssociateLastname(int salesforceId, String lastname){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update employee set lastname = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lastname);
            stmt.setInt(2, salesforceId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
    public void updateAssociateEmail(int salesforceId, String email){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "Update employee set email = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            stmt.setInt(2, salesforceId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

//    public void getBatchID(String email){
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet set = null;
//
//        try {
//            conn = ConnectionConfig.getConnection();
//            final String SQL = "Select batch";
//            stmt = conn.prepareStatement(SQL);
//            stmt.setString(1, email);
//            stmt.setInt(2, salesforceId);
//            stmt.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ResourceClosers.closeConnection(conn);
//            ResourceClosers.closeStatement(stmt);
//        }
//    }

}
