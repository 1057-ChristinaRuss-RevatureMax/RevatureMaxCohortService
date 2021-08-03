package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import models.AssociateDto;

public class AssociatePortfolioDaoImpl implements AssociatePortfolioDao {

    public AssociatePortfolioDaoImpl() {
		
	}
	
	public void createOne(String salesforceId){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "Insert INTO associate_portfolio values ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
        
    }
    
    @Override
    public void updateBio(String salesforceId, String bio){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "update associate_portfolio set bio = ? where salesforceId = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, bio);
			stmt.setString(2, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
        
    }
    @Override
    public void updatePreference(String salesforceId, String preference){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "update associate_portfolio set preference = ? where salesforceId = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, preference);
			stmt.setString(2, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
    }
    @Override
    public void updateFavoriteTechnologies(String salesforceId, String technologies){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "update associate_portfolio set favorite_technologies = ? where salesforceId = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, technologies);
			stmt.setString(2, salesforceId);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
    }

}