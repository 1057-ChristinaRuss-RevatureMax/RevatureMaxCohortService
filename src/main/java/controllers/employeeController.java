package controllers;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import services.employeeServiceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import config.LoggerConfig;
import io.javalin.http.Context;

public class employeeController {
    static employeeServiceImpl employeeService = new employeeServiceImpl();

    private static boolean validUser(String username){
        return(true);
    }

    public static void employeeHome(Context context){
        String username = context.sessionAttribute("session_username");
        boolean validation = employeeController.validUser(username);
        if (validation) {
            LoggerConfig.log(userController.class.getSimpleName(), "Current Session user is:  " + username);
            context.json("{Success: login successful} Now Home" + " " + username).status(200);
            context.render("/trainer-profile/trainer-profile.html");
        }
        else {
            context.redirect("/login");
        }
    }

    public static void editEmployee(Context context){
        if (context.method() == "POST") {
            String firstname = null;
            String lastname = null;
            String email = null;
            String bio = null;
            String technology = null;
            String location = null;
            //Sales force id will eventually come from session, this is just for testing
            int salesforceId;
            String body = context.body();
            
            JsonObject bodyJson = new Gson().fromJson(body, JsonObject.class);
            firstname = bodyJson.get("firstName").getAsString();
            lastname = bodyJson.get("lastName").getAsString();
            email = bodyJson.get("emailAddress").getAsString();
            bio = bodyJson.get("bio").getAsString();
            technology = bodyJson.get("technology").getAsString();
            location = bodyJson.get("location").getAsString();
            //Sales force id will eventually come from session, this is just for testing
            salesforceId = bodyJson.get("salesforceId").getAsInt();

            employeeService.editEmployee(salesforceId, firstname, lastname, email, bio, technology, location);

            //String salesforceId = context.sessionAttribute("salesforceId");


        }
    }
}
