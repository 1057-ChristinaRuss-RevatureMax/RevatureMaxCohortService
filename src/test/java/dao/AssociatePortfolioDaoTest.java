package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssociatePortfolioDaoTest {

    @BeforeMethod(groups = {"requireDB"})
    public void beforeMethod(){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionConfig.getConnection();
            stmt = conn.prepareStatement("INSERT into table VALUES(default, ?, ?, ?)");

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testUpdateBio(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceId = "";
        String bio = "";
        ResultSet result;
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set bio = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, bio);
            stmt.setString(2, salesforceId);
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testUpdatePreference(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceId = "";
        String preference = "";
        ResultSet result;
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set preference = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, preference);
            stmt.setString(2, salesforceId);
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testUpdateFavoriteTechnologies(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String technologies = "";
        String salesforceId = "";
        ResultSet result;
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set favorite_technologies = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, technologies);
            stmt.setString(2, salesforceId);
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

}
