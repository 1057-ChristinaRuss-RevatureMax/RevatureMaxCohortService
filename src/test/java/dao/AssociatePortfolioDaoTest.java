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

    @BeforeSuite(groups = {"requireDB"})
    public void beforeSuiteAssociate(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceId = "SF-1234";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        ResultSet result;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into associate values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, pswrd);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @BeforeSuite(groups = {"requireDB"})
    public void beforeSuite(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String bio = "I prefer to study on the weekends";
        String favorite_technologies = "Java";
        String preferences = "NY";
        String salesforceId = "SF-1234";

        try {
            conn = ConnectionConfig.getConnection();
            stmt = conn.prepareStatement("UPDATE associate_portfolio SET bio = ?, favorite_technologies = ?, preference = ? WHERE salesforceid = ?");
            stmt.setString(1, bio);
            stmt.setString(2, favorite_technologies);
            stmt.setString(3, preferences);
            stmt.setString(4, salesforceId);
            stmt.execute();

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
        String salesforceId = "SF-1234";
        String bio = "I like to party";
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set bio = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, bio);
            stmt.setString(2, salesforceId);
            stmt.execute();
            System.out.println("System successfully setup");

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testUpdatePreference(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceId = "SF-1234";
        String preference = "Dallas Texas";

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set preference = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, preference);
            stmt.setString(2, salesforceId);
            stmt.execute();

        } catch (SQLException e) {
            Assert.fail();
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
        String technologies = "PHP, Python, Selenium";
        String salesforceId = "SF-1234";
        ResultSet result;
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set favorite_technologies = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, technologies);
            stmt.setString(2, salesforceId);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testUpdateContactInformation(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String firstName = "Meg";
        String lastName = "johnson";
        String email = "megantheStallion@ah.com";
        String salesforceId = "SF-1234";
        ResultSet result;
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate set firstname = ?, lastname = ?, email = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, salesforceId);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @AfterSuite(groups = {"requireDB"})
    public void afterSuite() {
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceid = "SF-1234";

        try {
            conn = ConnectionConfig.getConnection();
            stmt = conn.prepareStatement("DELETE from associate_portfolio WHERE salesforceid = ?");
            stmt.setString(1, salesforceid);
            stmt.execute();
            System.out.println("Associate_portfolio System successfully reset");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }

        try {
                conn = ConnectionConfig.getConnection();
                stmt = conn.prepareStatement("DELETE from associate WHERE salesforceid = ?");
                stmt.setString(1, salesforceid);
                stmt.execute();
                System.out.println("Associate System successfully reset");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ResourceClosers.closeConnection(conn);
                ResourceClosers.closeStatement(stmt);
            }
    }
}
