package frontcontroller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/*
 * The front controller is the initial contact point form the Client.
 * - the front controller will contain your middleware
 */
public class FrontController {
    Javalin app;
    Dispatcher dispatcher;

    public FrontController(Javalin app) {
        this.app = app;
        this.app.before("/api/", FrontController::checkAllRequest);

        this.dispatcher = new Dispatcher(app);
    }

    public static void checkAllRequest(Context context) {
        System.out.println("The middleware has been hit");
    }
}
