package driver;

import config.JavalinConfig;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import config.ApiDatabaseConfig;

public class Main {
    public static void main(String[] args) {
        //Javalin app = Javalin.create().start(9001);
//        ApiDatabaseConfig.initAssociates();

        Javalin app = JavalinConfig.getApp(9001);

        new FrontController(app);
    }
}
