package frontcontroller;

import controllers.userController;
import controllers.employeeController;
import io.javalin.Javalin;

import config.LoggerConfig;

public class Dispatcher {

    public Dispatcher(Javalin app) {
//        app.after(ctx -> {
//            String batchid;
//            ctx.sessionAttribute("batchID", batchid);
//        })
        // Login and signup
        app.get("/", userController::loginUser);
        app.get("/login", userController::loginUser);
        app.post("/login", userController::loginUser);
        app.post("/associate", userController::editUser);
        app.post("/employee", employeeController::editEmployee);

        // Associate Home Page Controls
        app.get("/associateHome", userController::associateHome);
        app.get("/employeeHome", employeeController::employeeHome);
        app.get("/passwordchange", userController::passwordChange);
        app.post("/passwordchange", userController::passwordChange);
        app.get("/associateprofile", ctx ->{
        	ctx.render("/associate-profile/associate-profile.html");
        });
        app.get("/editassociateprofile", userController::editUser);
        app.post("/editassociateprofile", userController::editUser);
        app.get("/trainerprofile", ctx ->{
            ctx.render("/trainer-profile/trainer-profile.html");
        });
        app.post("/edittrainerprofile", employeeController::editEmployee);
        app.get("/edittrainerprofile", employeeController::editEmployee);
//                associate-dashboard
//                        trainer-dashboard
//                                trainer-batch-view
//        app.get("/associate/<id>", userController::associateProfile);


        // invalid credentials on login
        app.get("/invalid", ctx -> {
            if (ctx.sessionAttribute("session_username") == ("invalid")) {
                ctx.json("invalid");
                ctx.sessionAttribute("session_username", null);
            }
    });

        app.get("/invalidpassword", ctx ->{
            if(ctx.sessionAttribute("password_msg") == null) {
                ctx.json("isNull");
            }
            else if (ctx.sessionAttribute("password_msg").equals("invalid")) {
                ctx.json("invalid");
                ctx.sessionAttribute("password_msg", null);
            }
            else if(ctx.sessionAttribute("password_msg").equals("unmatched")) {
                ctx.json("unmatched");
                ctx.sessionAttribute("password_msg", null);
            }
        });

        app.get("/logout", userController::logout);
}
}
