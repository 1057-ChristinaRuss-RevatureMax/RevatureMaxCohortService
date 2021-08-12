package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDaoTest {
    private EmployeeDaoImpl edao = new EmployeeDaoImpl();

    @Test(groups = {"requireDB"})
    public void testCreateEmployee() {
        Integer salesforceId = 123456;
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        edao.createEmployee(salesforceId, firstName, lastName, email, pswrd);
        Assert.assertEquals(edao.getEmployeeBySalesforce(salesforceId).getSalesforceId(), 123456);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateEmployeeFirstName() {
        Integer salesforceId = 123456;
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        String newFirstName = "chorito";
        edao.createEmployee(salesforceId, firstName, lastName, email, pswrd);

        edao.updateAssociateFirstname(salesforceId, newFirstName);
        Assert.assertEquals(edao.getEmployeeBySalesforce(salesforceId).getFirstName(), newFirstName);
        edao.updateAssociateFirstname(salesforceId, firstName);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateEmployeeLastName() {
        Integer salesforceId = 123456;
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        String newLastName = "Lola";
        edao.createEmployee(salesforceId, firstName, lastName, email, pswrd);

        edao.updateAssociateLastname(salesforceId, newLastName);
        Assert.assertEquals(edao.getEmployeeBySalesforce(salesforceId).getLastName(), newLastName);
        edao.updateAssociateLastname(salesforceId, lastName);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateEmployeeEmail() {
        Integer salesforceId = 123456;
        String firstName = "Mockito";
        String lastName = "Test";
        String email = "mockito.test@revature.com";
        String pswrd = "cocktail";
        String newEmail = "mockito.pass@revature.net";
        edao.createEmployee(salesforceId, firstName, lastName, email, pswrd);

        edao.updateAssociateEmail(salesforceId, newEmail);
        Assert.assertEquals(edao.getEmployeeBySalesforce(salesforceId).getEmail(), newEmail);
        edao.updateAssociateEmail(salesforceId, email);
    }

}