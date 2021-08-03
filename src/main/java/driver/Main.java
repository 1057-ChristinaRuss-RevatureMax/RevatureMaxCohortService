package driver;

import config.JavalinConfig;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import config.ApiDatabaseConfig;

public class Main {
    public static void main(String[] args) {

        Javalin app = JavalinConfig.getApp(9001);
        ApiDatabaseConfig.initAssociates();
        FrontController fc = new FrontController(app);
    }
}

