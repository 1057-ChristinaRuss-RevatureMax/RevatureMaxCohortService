package controllers;

import org.json.JSONObject;
import org.openqa.selenium.json.Json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import config.LoggerConfig;
import io.javalin.http.Context;

import driver.Main;
import org.apache.commons.io.FileUtils;
import services.userService;
import services.userServiceImpl;
import dao.AssociateDaoImpl;

import java.util.HashMap;

public class userController {

    static userService userservice = new userServiceImpl();
    // PORT NUMBER MIGHT NEED TO BE REFACTORED

    // FOR LOGIN
    public static void loginUser(Context context) {
        if (context.method() == "POST") {
            String username = null;
            String password = null;

            username = context.formParam("username");
            password = context.formParam("password");
            boolean login = userservice.loginUser(username, password);
            if (login == false) {
                context.sessionAttribute("session_username", "invalid");
            }
            if (username != null && password != null && login) {
                LoggerConfig.log(userController.class.getSimpleName(), "User logged in: " + username);
                context.sessionAttribute("session_username", username);
                //context.sessionAttribute("salesforceId", userservice.getSalesForceId(username));
                context.json("{Success: login successful}").status(200);
            }
            else {
                context.sessionAttribute("session_username", username);
                LoggerConfig.log(userController.class.getSimpleName(), "User login failed, username: " + username);
                context.sessionAttribute("session_username", "invalid");
                context.redirect("/login");

            }

        } else {
            context.render("/index/index.html");
        }
    }

    public static void editUser(Context context){
        if (context.method() == "POST") {
            String firstname = null;
            String lastname = null;
            String email = null;
            String bio = null;
            String favorite_tech = null;
            String preference = null;
            //Sales force id will eventually come from session, this is just for testing
            String salesforceId = null;
            String body = context.body();
            
            JsonObject bodyJson = new Gson().fromJson(body, JsonObject.class);
            firstname = bodyJson.get("firstName").getAsString();
            lastname = bodyJson.get("lastName").getAsString();
            email = bodyJson.get("emailAddress").getAsString();
            bio = bodyJson.get("bio").getAsString();
            favorite_tech = bodyJson.get("favoriteTechnologies").getAsString();
            preference = bodyJson.get("preference").getAsString();
            //Sales force id will eventually come from session, this is just for testing
            salesforceId = bodyJson.get("salesforceId").getAsString();

            userservice.editUser(salesforceId, firstname, lastname, email, bio, favorite_tech, preference);

            //String salesforceId = context.sessionAttribute("salesforceId");


        }

    }
}