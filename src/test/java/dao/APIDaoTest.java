package dao;

import config.ConnectionConfig;
import config.RDSConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class APIDaoTest {

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
    public void testLoginUser(){
        Connection conn = null;
        PreparedStatement stmt = null;
        String username = "mock17.associate655088fb-9cce-4c1b-add6-bbdd252465cd@mock.com";
        String password = "password";
        ResultSet result = null;

        try {
            //Establish the connection to the DB
            conn = ConnectionConfig.getConnection();
            final String SQL = "SELECT email, pswrd FROM associate where email=? AND pswrd=?";
            stmt = conn.prepareStatement(SQL);

            stmt.setString(1, username);
            stmt.setString(2, password);

            result = stmt.executeQuery();

            boolean exists = result.next();

            Assert.assertEquals(exists, true);

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
    }

    @Test(groups = {"requireDB"})
    public void testGetAllUsers(){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        HttpURLConnection connection = null;

        try {
            URL url = new URL("https://caliber2-mock.revaturelabs.com/mock/training/associate");

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);


            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        StringBuffer paramBody = new StringBuffer(responseContent);
        Assert.assertEquals(paramBody.toString(), responseContent.toString());
    }
}