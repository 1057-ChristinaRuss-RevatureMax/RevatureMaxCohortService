package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class employeePortfolioDaoTest {

    private EmployeePortfolioDao epd = new EmployeePortfolioDao();

    @Test(groups = {"requireDB"})
    public void testCreateOne() {


    int salesforceid = 123456;

    epd.createOne(salesforceid);
    Assert.assertEquals(epd.getPortfolioBySalesforce(salesforceid).getSalesForceId(), 123456);
}

    @Test(groups = {"requireDB"})
    public void testUpdateTechnology(){
        String technology = "Java, Python";
        String newtech = "C++, Hadoop";
        int salesforceid = 123456;
        epd.updateTechnology(salesforceid, newtech);
        Assert.assertEquals(epd.getPortfolioBySalesforce(salesforceid).getTechnology(), newtech);
        epd.updateTechnology(salesforceid, technology);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateLocation(){
        String location = "New York";
        String newloca = "Texas";
        int salesforceid = 123456;
        epd.updateLocation(salesforceid, newloca);
        Assert.assertEquals(epd.getPortfolioBySalesforce(salesforceid).getLocation(), newloca);
        epd.updateTechnology(salesforceid, location);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateBio(){
        String bio = "Im addicted to pizza";
        String newbio = "I hate pizza";
        int salesforceid = 123456;
        epd.updateBio(salesforceid, newbio);
        Assert.assertEquals(epd.getPortfolioBySalesforce(salesforceid).getBio(), newbio);
        epd.updateTechnology(salesforceid, bio);
    }
}