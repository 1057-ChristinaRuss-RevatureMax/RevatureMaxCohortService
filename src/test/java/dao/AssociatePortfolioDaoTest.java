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

<<<<<<< HEAD
    @BeforeSuite(groups = {"requireDB"})
    public void beforeSuite(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String bio = "I prefer to study on the weekends";
        String favorite_technologies = "Java, HTML, CSS";
        String preferences = "New York";
        String salesforceid = "12345";

        try {
            conn = ConnectionConfig.getConnection();
            stmt = conn.prepareStatement("INSERT into associate_portfolio VALUES(?,?,?,?)");
            stmt.setString(1, bio);
            stmt.setString(2,favorite_technologies);
            stmt.setString(3,preferences);
            stmt.setString(4,salesforceid);
            stmt.execute();
=======
    @BeforeMethod(groups = {"requireDB"})
    public void beforeMethod(){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionConfig.getConnection();
            stmt = conn.prepareStatement("INSERT into table VALUES(default, ?, ?, ?)");
>>>>>>> 0f1af99 (Started unit testing)

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
<<<<<<< HEAD
        String salesforceId = "12345";
        String bio = "I like to party";
=======
        String salesforceId = "";
        String bio = "";
        ResultSet result;
>>>>>>> 0f1af99 (Started unit testing)
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set bio = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, bio);
            stmt.setString(2, salesforceId);
<<<<<<< HEAD
            stmt.execute();
            System.out.println("System successfully setup");

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
=======
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());

        } catch (SQLException e) {
            e.printStackTrace();
>>>>>>> 0f1af99 (Started unit testing)
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testUpdatePreference(){
        Connection conn = null;
        PreparedStatement stmt = null;
<<<<<<< HEAD
        String salesforceId = "12345";
        String preference = "Dallas Texas";

=======
        String salesforceId = "";
        String preference = "";
        ResultSet result;
>>>>>>> 0f1af99 (Started unit testing)
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set preference = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, preference);
            stmt.setString(2, salesforceId);
<<<<<<< HEAD
            stmt.execute();

        } catch (SQLException e) {
            Assert.fail();
=======
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());

        } catch (SQLException e) {
>>>>>>> 0f1af99 (Started unit testing)
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
<<<<<<< HEAD
        String technologies = "PHP, Python, Selenium";
        String salesforceId = "12345";
=======
        String technologies = "";
        String salesforceId = "";
>>>>>>> 0f1af99 (Started unit testing)
        ResultSet result;
        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "update associate_portfolio set favorite_technologies = ? where salesforceId = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, technologies);
            stmt.setString(2, salesforceId);
<<<<<<< HEAD
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
=======
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());

        } catch (SQLException e) {
            e.printStackTrace();
>>>>>>> 0f1af99 (Started unit testing)
        }finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

<<<<<<< HEAD
    @AfterSuite
    public void afterSuite(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceid = "12345";

        try {
            conn = ConnectionConfig.getConnection();
            stmt = conn.prepareStatement("DELETE from associate_portfolio WHERE salesforceid = ?");
            stmt.setString(1, salesforceid);
            stmt.execute();
            System.out.println("System successfully reset");

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
}
=======
}
>>>>>>> 0f1af99 (Started unit testing)
