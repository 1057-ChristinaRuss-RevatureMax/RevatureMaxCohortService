package frontcontroller;

import controllers.userController;
import io.javalin.Javalin;

import config.LoggerConfig;

public class Dispatcher {

    public Dispatcher(Javalin app) {

        // Login and signup
        app.get("/", userController::loginUser);
        app.get("/login", userController::loginUser);
        app.post("/login", userController::loginUser);

        // Associate Home Page Controls
        app.get("/home", userController::home);
//        app.get("/associate", userController::
//        trainer view/
//        edit profile trainer
//                edit profile associate
//                change password trainer
//                change password associate
        app.get("/passwordchange", userController::passwordChange);
        app.post("/passwordchange", userController::passwordChange);
//                associate-dashboard
//                        trainer-dashboard
//                                trainer-batch-view


        // invalid credentials on login
        app.get("/invalid", ctx -> {
        	if (ctx.sessionAttribute("session_username").equals("invalid")) {
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
