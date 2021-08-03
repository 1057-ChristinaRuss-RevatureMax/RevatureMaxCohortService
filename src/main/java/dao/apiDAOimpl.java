package dao;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import config.ConnectionConfig;
import config.LoggerConfig;
import config.RDSConnectionConfig;
import config.ResourceClosers;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class apiDAOimpl implements apiDAO{

    public apiDAOimpl(){

    }

    @Override
    public boolean loginUser(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
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

            return exists;

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(conn);
            ResourceClosers.closeStatement(stmt);
        }
        return false;
    }

    public static String getAllUsers()
    {
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
        return paramBody;
    }
}