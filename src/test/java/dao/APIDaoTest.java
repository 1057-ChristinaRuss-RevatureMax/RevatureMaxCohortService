package dao;

import config.ConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class APIDaoTest {
    private apiDAOimpl apiDAOimpl;

    @BeforeTest
    void init() { apiDAOimpl = new apiDAOimpl();}
    
    @Test(groups = {"requireDB"})
    public void testLoginUser(){
        Assert.assertEquals(apiDAOimpl.loginUser("mock11.associatee55fc94f-79e6-469b-8b19-95448707b944@mock.com", "password"), true);
    }

    @Test(groups = {"requireDB"})
    public void testGetAllusers(){
        Assert.assertNotNull(dao.apiDAOimpl.getAllUsers());
    }

    @Test(groups = {"requireDB"})
    public void testCheckSessions(){
        Assert.assertEquals(apiDAOimpl.checkSession("mock11.associatee55fc94f-79e6-469b-8b19-95448707b944@mock.com"), true);
    }

    @Test(groups = {"requireDB"})
    public void testPasswordChange(){
        Assert.assertTrue(apiDAOimpl.passwordChange("mock11.associatee55fc94f-79e6-469b-8b19-95448707b944@mock.com", "password"));
    }

    @Test(groups = {"requireDB"})
    public void testGetAllEmployees(){
        Assert.assertNotNull(dao.apiDAOimpl.getAllEmployees());
    }
}