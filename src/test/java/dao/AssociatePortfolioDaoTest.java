package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import models.AssociatePortfolio;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AssociatePortfolioDaoTest {
    private AssociatePortfolioDaoImpl apidaoimpl = new AssociatePortfolioDaoImpl();;


    @Test(groups = {"requireDB"})
    public void testCreateOne(){
        String salesforceID = "12345";
        apidaoimpl.createOne(salesforceID);
        Assert.assertEquals(apidaoimpl.getPortfolioBySalesforce(salesforceID).getSalesForceId(), salesforceID);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateBio(){
        String salesforceID = "12345";
        String bio = "I like pizza";
        String ogBio = null;
        apidaoimpl.updateBio(salesforceID, bio);
        Assert.assertEquals(apidaoimpl.getPortfolioBySalesforce(salesforceID).getBio(), bio);
        apidaoimpl.updateBio(salesforceID, ogBio);
    }

    @Test(groups = {"requireDB"})
    public void testUpdatePreference(){
        String salesforceID = "12345";
        String preference = "NY";
        String ogPref = null;
        apidaoimpl.updatePreference(salesforceID, preference);
        Assert.assertEquals(apidaoimpl.getPortfolioBySalesforce(salesforceID).getPreference(), preference);
        apidaoimpl.updatePreference(salesforceID, ogPref);
    }

    @Test(groups = {"requireDB"})
    public void testUpdateFavoriteTechnologies(){
        String salesforceID = "12345";
        String technologies = "Java, Python";
        String ogStack = null;
        apidaoimpl.updateFavoriteTechnologies(salesforceID, technologies);
        Assert.assertEquals(apidaoimpl.getPortfolioBySalesforce(salesforceID).getFavoriteTechnology(),technologies);
        apidaoimpl.updateFavoriteTechnologies(salesforceID, ogStack);
    }


}