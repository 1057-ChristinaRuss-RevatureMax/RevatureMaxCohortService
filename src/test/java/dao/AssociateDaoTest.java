package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AssociateDaoTest{


    @Test(groups = {"requireDB"})
    public void testCreateAssociate(){
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

    @Test(groups = {"requireDB"})
    public void testGetAssociateBySalesforce(){
        Associate associate = new Associate();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set;
        String salesforceId = "SF-1234";

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
            Assert.assertEquals(associate.getPassword(), "cocktail");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testGetAssociateByEmail() {
        Associate associate = new Associate();
        Connection conn = null;
        PreparedStatement stmt = null;
        String email = "mock5.associateb1fb89f2-bab6-4719-bc1a-4f6cda2b209f@mock.com";
        ResultSet set = null;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "select * from associate where email = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, email);
            set = stmt.executeQuery();
            while (set.next()) {
                associate.setSalesforceId(set.getString(1));
                associate.setFirstname(set.getString(2));
                associate.setLastname(set.getString(3));
                associate.setEmail(set.getString(4));
                associate.setPassword(set.getString(5));
            }
            Assert.assertEquals(associate.getPassword(), "password");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @DataProvider (name = "edit-password")
    public Object[][] editPasswordObject(){
        return new Object[][] { { "SF-1234", "banana"}};
    }

    @Test(groups = {"requireDB"}, dataProvider = "edit-password")
    public void testChangePassword(String salesforceId, String pswrd){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ConnectionConfig.getConnection();
            final String SQL = "UPDATE associate SET pswrd = ? WHERE salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, pswrd);
            stmt.setString(2, salesforceId);
            stmt.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            Assert.fail();
        }
        finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }


    @BeforeSuite
    public void beforeSuite() {
        Connection conn = null;
        PreparedStatement stmt = null;
        String saleid = "SF-1234";

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "DELETE from associate WHERE salesforceid = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, saleid);
            stmt.executeUpdate();

            System.out.println("Associate Suite reset successful");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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