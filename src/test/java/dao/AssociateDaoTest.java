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


public class AssociateDaoTest{

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
    public void testCreateAssociate(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String salesforceId = "12345";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pass_word = "cocktail";
        ResultSet result;

        try {
            conn = ConnectionConfig.getConnection();
            final String SQL = "insert into associate values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, salesforceId);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, pass_word);
            result = stmt.executeQuery();
            Assert.assertTrue(result.next());
        } catch (SQLException e) {
            e.printStackTrace();
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
        String email = "";
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
            Assert.assertEquals(associate.getPassword(), "cocktail");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }
}