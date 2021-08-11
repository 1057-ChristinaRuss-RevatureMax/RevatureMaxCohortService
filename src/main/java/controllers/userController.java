package controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import config.LoggerConfig;
import io.javalin.http.Context;

import driver.Main;
import org.apache.commons.io.FileUtils;
import services.userService;
import services.userServiceImpl;
import services.employeeServiceImpl;
import dao.AssociateDaoImpl;
import models.Associate;
import models.AssociatePortfolio;

import java.util.HashMap;
import java.util.Map;

public class userController {

    static userService userservice = new userServiceImpl();
    static employeeServiceImpl empservice = new employeeServiceImpl();
    // PORT NUMBER MIGHT NEED TO BE REFACTORED

    // FOR LOGIN
    public static void loginUser(Context context) {
        if (context.method() == "POST") {
            String username = null;
            String password = null;

            username = context.formParam("username");
            password = context.formParam("password");
            boolean login = userservice.loginUser(username, password);
            boolean employeelogin = empservice.loginEmployee(username, password);
            if (login == false) {
                context.sessionAttribute("session_username", "invalid");
            }
            if (username != null && password != null && login) {
                LoggerConfig.log(userController.class.getSimpleName(), "User logged in: " + username);
                context.sessionAttribute("session_username", username);
                context.sessionAttribute("salesforceId", userservice.getSalesForceId(username));
                context.json("{Success: login successful}").status(200);
                context.redirect("/associateHome");

            }
            else if(username != null && password != null && employeelogin) {
                LoggerConfig.log(userController.class.getSimpleName(), "User logged in: " + username);
                context.sessionAttribute("session_username", username);
                context.sessionAttribute("salesforceId", empservice.getSalesForceId(username));
                context.json("{Success: login successful}").status(200);
                context.redirect("/employeeHome");
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

    public static void passwordChange(Context context) {
        String username = context.sessionAttribute("session_username");
        boolean validation = userController.validUser(username);
        if (validation) {
            LoggerConfig.log(userController.class.getSimpleName(), "Current Session user is:  " + username);
        }
        else{
            context.redirect("/login");
        }
        if (context.method() == "POST") {
            String oldPassword = context.formParam("oldpassword");
            String newPassword = context.formParam("newpassword");
            String retype = context.formParam("retype");
            String user = context.sessionAttribute("session_username");

            if (!newPassword.equals(retype)) {
                context.sessionAttribute("password_msg", "unmatched");
                context.redirect("/passwordchange");
            }
            else if (!userservice.loginUser(user, oldPassword)) {
                context.sessionAttribute("password_msg", "invalid");
                context.redirect("/passwordchange");
            }
            else if (userservice.passwordChange(user, newPassword)) {
                context.redirect("http://localhost:9001/associateHome");
            };


        } else {
            context.render("/password-change/password-change.html");
        }
    }

    public static boolean validUser(String username){
        boolean validation = userservice.checkSession(username);
        return validation;
    }

    public static String getBatchID(String username){
        String batchID = userservice.getBatchID(username);
        return batchID;
    }


    public static void logout(Context context){
        context.req.getSession().invalidate();
        context.redirect("/login");
    }

    public static void associateHome(Context context) {
        String username = context.sessionAttribute("session_username");
        boolean validation = userController.validUser(username);
        if (validation) {
            String batchID = userController.getBatchID(username);
            context.cookie("email", username);
            context.cookie("batchID", batchID);

            LoggerConfig.log(userController.class.getSimpleName(), "Current Session user is:  " + username);
            context.json("{Success: login successful} Now Home" + " " + username).status(200);
            context.render("/associate-dashboard/associate-dashboard.html");
        }
        else {
            context.redirect("/login");
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
            String body = context.body();
            
            JsonObject bodyJson = new Gson().fromJson(body, JsonObject.class);
            firstname = bodyJson.get("firstName").getAsString();
            lastname = bodyJson.get("lastName").getAsString();
            email = bodyJson.get("emailAddress").getAsString();
            bio = bodyJson.get("bio").getAsString();
            favorite_tech = bodyJson.get("favoriteTechnologies").getAsString();
            preference = bodyJson.get("preference").getAsString();
            //Sales force id will eventually come from session, this is just for testing
            String salesforceId = context.sessionAttribute("salesforceId");

            userservice.editUser(salesforceId, firstname, lastname, email, bio, favorite_tech, preference);
            context.redirect("http://localhost:9001/associateHome");
        }
        else{
            Map<String, String> map = new HashMap<String, String>();
            Associate user = userservice.getUserBySalesForceId(context.sessionAttribute("salesforceId"));
            AssociatePortfolio portfolio = userservice.getPortfolioBySalesForceId(context.sessionAttribute("salesforceId"));
            map.put("firstName", user.getFirstname());
            map.put("lastName", user.getLastname());
            map.put("emailAddress", user.getEmail());
            map.put("bio", portfolio.getBio());
            map.put("favoriteTechnologies", portfolio.getFavoriteTechnology());
            map.put("preference", portfolio.getPreference());
            context.json(map);
            System.out.println("sent Json");
        }

    }
}