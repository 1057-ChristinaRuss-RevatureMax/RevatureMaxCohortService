package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import org.testng.Assert;
import org.testng.annotations.*;

<<<<<<< HEAD
import javax.annotation.Resource;
=======
>>>>>>> 0f1af99 (Started unit testing)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AssociateDaoTest{

<<<<<<< HEAD
=======
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
>>>>>>> 0f1af99 (Started unit testing)

    @Test(groups = {"requireDB"})
    public void testCreateAssociate(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceId = "12345";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
<<<<<<< HEAD
        String pswrd = "cocktail";
=======
        String pass_word = "cocktail";
>>>>>>> 0f1af99 (Started unit testing)
        ResultSet result;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into associate values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
<<<<<<< HEAD
            stmt.setString(5, pswrd);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
=======
            stmt.setString(5, pass_word);
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());
        } catch (SQLException e) {
            e.printStackTrace();
>>>>>>> 0f1af99 (Started unit testing)
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
        String salesforceId = "12345";

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
<<<<<<< HEAD
        String email = "mock5.associateb1fb89f2-bab6-4719-bc1a-4f6cda2b209f@mock.com";
=======
        String email = "";
>>>>>>> 0f1af99 (Started unit testing)
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
<<<<<<< HEAD
            Assert.assertEquals(associate.getPassword(), "password");
=======
            Assert.assertEquals(associate.getPassword(), "cocktail");
>>>>>>> 0f1af99 (Started unit testing)
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
<<<<<<< HEAD

    @DataProvider (name = "edit-password")
    public Object[][] editPasswordObject(){
        return new Object[][] { { "12345", "banana"}};
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
        String saleid = "12345";

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "DELETE from associate WHERE salesforceid = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, saleid);
            stmt.executeUpdate();

            System.out.println("Suite reset successful");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }

    }
}
=======
}
>>>>>>> 0f1af99 (Started unit testing)
