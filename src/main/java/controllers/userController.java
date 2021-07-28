package controllers;

import config.LoggerConfig;
import io.javalin.http.Context;

import driver.Main;
import org.apache.commons.io.FileUtils;
import services.userService;
import services.userServiceImpl;

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
            if (username != null && password != null && login) {
                LoggerConfig.log(userController.class.getSimpleName(), "User logged in: " + username);
                context.sessionAttribute("session_username", username);
                context.json("{Success: login successful}").status(200);

            } else {
                LoggerConfig.log(userController.class.getSimpleName(), "User login failed, username: " + username);
                context.json("{}").status(400);
            }


        } else {
            context.render("/index/index.html");
        }
    }
}