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
import models.Employee;
import models.EmployeePortfolio;

import java.util.HashMap;
import java.util.Map;


public class employeeController {
    static employeeServiceImpl employeeService = new employeeServiceImpl();

    private static boolean validUser(String username){
        return(true);
    }

    public static void employeeHome(Context context) {
        String username = context.sessionAttribute("session_username");
        System.out.println("User is verified");
        boolean validation = employeeController.validUserTrainer(username);
        if (validation) {
            LoggerConfig.log(userController.class.getSimpleName(), "Current Session user is:  " + username);
            context.json("{Success: login successful} Now Home" + " " + username).status(200);
            context.render("/trainer-dashboard/trainer-dashboard.html");
        }
        else {
            context.redirect("/login");
        }
    }

    public static boolean validUserTrainer(String username){
        userService service = new userServiceImpl();
        boolean validUser = service.checkSessionTrainer(username);
        return validUser;
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
        else {
            Map<String, String> map = new HashMap<String, String>();
            Employee employee = employeeService.getEmployeeBySalesForceId(context.sessionAttribute("salesforceId"));
            EmployeePortfolio portfolio = employeeService.getPortfolioBySalesForceId(context.sessionAttribute("salesforceId"));
            map.put("firstName", employee.getFirstName());
            map.put("lastName", employee.getLastName());
            map.put("emailAddress", employee.getEmail());
            map.put("bio", portfolio.getBio());
            map.put("specialization", portfolio.getTechnology());
            map.put("location", portfolio.getLocation());
            context.json(map);
            System.out.println("sent Json");
        }

    }
}
