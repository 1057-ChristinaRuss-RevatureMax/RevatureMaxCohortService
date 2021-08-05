package frontcontroller;

import controllers.userController;
import controllers.employeeController;
import io.javalin.Javalin;

import config.LoggerConfig;

public class Dispatcher {

    public Dispatcher(Javalin app) {

        // Login and signup
        app.get("/", userController::loginUser);
        app.get("/login", userController::loginUser);
        app.post("/login", userController::loginUser);
        app.post("/associate/edit", userController::editUser);
        app.post("/employee/edit", employeeController::editEmployee);

        // Associate Home Page Controls
        //app.get("/home", userController::home);
//        app.get("/associate", userController::
//        trainer view/
//        edit profile trainer
//                edit profile associate
//                change password trainer
//                change password associate
//                associate-dashboard
//                        trainer-dashboard
//                                trainer-batch-view


        // invalid credentials on login
        app.get("/invalid", ctx -> {
            if (ctx.sessionAttribute("session_username") == ("invalid")) {
                ctx.json("invalid");
                ctx.sessionAttribute("session_username", null);
            }
    });
        //app.get("/logout", userController::logout);
}
}
