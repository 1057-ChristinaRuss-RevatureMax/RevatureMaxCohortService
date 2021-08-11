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
import models.AssociateDto;
import models.AssociatePortfolio;

public class AssociatePortfolioDaoImpl implements AssociatePortfolioDao {

    public AssociatePortfolioDaoImpl() {
		
	}
	@Override
	public void createOne(String salesforceId) {
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = ConnectionConfig.getConnection();
			final String SQL = "Insert into associate_portfolio values (?, NULL, NULL, NULL) ON CONFLICT DO nothing";
			assert conn != null;
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
			assert conn != null;
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
			assert conn != null;
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
			assert conn != null;
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

	public AssociatePortfolio getPortfolioBySalesforce(String salesforceId) {
        AssociatePortfolio portfolio = new AssociatePortfolio();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select * from associate_portfolio where salesforceId = ?";
			assert conn != null;
			stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            set = stmt.executeQuery();
            while(set.next()) {
                portfolio.setSalesForceId(set.getString(1));
                portfolio.setBio(set.getString(2));
                portfolio.setFavoriteTechnology(set.getString(3));
                portfolio.setPreference(set.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
            return portfolio;
        }
    }

}