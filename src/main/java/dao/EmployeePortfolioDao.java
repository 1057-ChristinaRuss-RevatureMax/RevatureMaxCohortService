package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.SHA256Digest;

import java.sql.ResultSet;
import java.sql.SQLException;
import config.RDSConnectionConfig;
import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import models.EmployeePortfolio;
import models.AssociateDto;

public class EmployeePortfolioDao {
    public void createOne(int salesforceId) {
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "Insert into employee_portfolio values (?, NULL, NULL, NULL) ON CONFLICT DO nothing";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
		
    }
    public void updateBio(int salesforceId, String bio){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "update employee_portfolio set bio = ? where salesforceId = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, bio);
			stmt.setInt(2, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
        
	}
	
	public EmployeePortfolio getPortfolioBySalesForceId(int salesforceId){
		EmployeePortfolio portfolio = new EmployeePortfolio();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select * from employee_portfolio where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, salesforceId);
            set = stmt.executeQuery();
            while(set.next()) {
                portfolio.setSalesForceId(set.getString(1));
                portfolio.setBio(set.getString(2));
                portfolio.setTechnology(set.getString(3));
                portfolio.setLocation(set.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return portfolio;
        }
	}

    public void updateTechnology(int salesforceId, String technology){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "update employee_portfolio set technology = ? where salesforceId = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, technology);
			stmt.setInt(2, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
    }
    public void updateLocation(int salesforceId, String location){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "update employee_portfolio set trainer_location = ? where salesforceId = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, location);
			stmt.setInt(2, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
    }

}
