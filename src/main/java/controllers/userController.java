package controllers;

import config.LoggerConfig;
import io.javalin.http.Context;

import services.userService;
import services.userServiceImpl;

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
                context.json("{Success: login successful}").status(200);
                context.redirect("/associateHome");
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

    private static boolean validUser(String username){
        boolean validation = userservice.checkSession(username);
        return validation;
    }

    public static void associateHome(Context context) {
        String username = context.sessionAttribute("session_username");
        boolean validation = userController.validUser(username);
        if (validation) {
            LoggerConfig.log(userController.class.getSimpleName(), "Current Session user is:  " + username);
            context.json("{Success: login successful} Now Home" + " " + username).status(200);
            context.render("/AssociateDashboard/associate-dashboard.html");
        }
        else {
            context.redirect("/login");
        }
    }

    public static void logout(Context context){
        context.req.getSession().invalidate();
        context.redirect("/login");
    }
}