package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.EmployeeDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {
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

    @Override
    public EmployeeDB getEmployeeBySalesforce(int salesforceId) {
        EmployeeDB employee;
        employee = new EmployeeDB();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select * from employee where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, salesforceId);
            set = stmt.executeQuery();
            while(set.next()) {
                employee.setSalesforceId(set.getInt(1));
                employee.setFirstName(set.getString(2));
                employee.setLastName(set.getString(3));
                employee.setEmail(set.getString(4));
                employee.setPassword(set.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return employee;
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
}
