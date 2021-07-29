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
        app.get("/invalid", ctx -> {
            if (ctx.sessionAttribute("session_username").equals("invalid")) {
                ctx.json("invalid");
                ctx.sessionAttribute("session_username", null);
            }
    });
}
}
