package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import models.AssociateAssignment;
import models.AssociatePortfolio;
import models.Flag;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AssociateDaoTest{

    private AssociateDaoImpl associatedaoimpl = new AssociateDaoImpl();

    @Test(groups = {"requireDB"})
    public void testCreateAssociate() {
        String salesforceId = "12345";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        associatedaoimpl.createAssociate(salesforceId,firstName,lastName,email,pswrd);
        Assert.assertEquals(associatedaoimpl.getAssociateByEmail(email).getEmail(), email);
    }

    @Test(groups = {"requireDB"})
    public void testGetAssociateByEmail() {
        Associate aport = associatedaoimpl.getAssociateByEmail("mock11.associatee55fc94f-79e6-469b-8b19-95448707b944@mock.com");
        Assert.assertEquals(aport.getEmail(), "mock11.associatee55fc94f-79e6-469b-8b19-95448707b944@mock.com");
    }

    @Test(groups = {"requireDB"})
    public void testUpdateAssociateFirstName(){
        String salesforceId = "12345";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        String newFirstName = "chorito";
        associatedaoimpl.createAssociate(salesforceId,firstName,lastName,email,pswrd);

        associatedaoimpl.updateAssociateFirstname(salesforceId, newFirstName);
        Assert.assertEquals(associatedaoimpl.getAssociateBySalesforce(salesforceId).getFirstname(), newFirstName);
        associatedaoimpl.updateAssociateFirstname(newFirstName, salesforceId);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateAssociateLastName(){
        String salesforceId = "12345";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        String newLastName = "Lallulu";
        associatedaoimpl.createAssociate(salesforceId,firstName,lastName,email,pswrd);

        associatedaoimpl.updateAssociateLastname(salesforceId, newLastName);
        Assert.assertEquals(associatedaoimpl.getAssociateBySalesforce(salesforceId).getLastname(), newLastName);
        associatedaoimpl.updateAssociateLastname(salesforceId, lastName);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateAssociateEmail(){
        String salesforceId = "12345";
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        String newEmail = "mockito-pass@revature.net";
        associatedaoimpl.updateAssociateEmail(salesforceId, newEmail);
        Assert.assertEquals(associatedaoimpl.getAssociateBySalesforce(salesforceId).getEmail(), newEmail);
        associatedaoimpl.updateAssociateEmail(salesforceId, email);
    }


    @Test(groups = {"requireDB"})
    public void testGetAssociateSalesforceID(){
        Associate aport = associatedaoimpl.getAssociateBySalesforce("SF-2292");
        Assert.assertEquals(aport.getSalesforceId(), "SF-2292");
    }

//    @BeforeSuite
//    public void beforeSuite() {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        String saleid = "12345";
//
//        try {
//            conn = ConnectionConfig.getConnection();
//            final String SQL = "DELETE from associate WHERE salesforceid = ?";
//            stmt = conn.prepareStatement(SQL);
//            stmt.setString(1, saleid);
//            stmt.executeUpdate();
//
//            System.out.println("Suite reset successful");
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            ResourceClosers.closeConnection(conn);
//            ResourceClosers.closeStatement(stmt);
//        }
//
//    }
}