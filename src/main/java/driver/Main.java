package driver;

import config.JavalinConfig;
import frontcontroller.FrontController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        //Javalin app = Javalin.create().start(9001);
        Javalin app = JavalinConfig.getApp(9001);

        FrontController fc = new FrontController(app);
    }
}