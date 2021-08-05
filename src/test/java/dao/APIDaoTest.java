package dao;

import config.ConnectionConfig;
import config.RDSConnectionConfig;
import config.ResourceClosers;
import models.Associate;
import org.testng.Assert;
import org.testng.annotations.*;

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
        String username = "";
        String password = "";
        ResultSet result = null;

        try {
            //Establish the connection to the DB
            conn = RDSConnectionConfig.getConnection();
            final String SQL = "SELECT email, pass_word FROM associate where email=? AND pass_word=?";
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
        String paramBody = new String(responseContent);
        Assert.assertEquals(paramBody, responseContent);
    }
}