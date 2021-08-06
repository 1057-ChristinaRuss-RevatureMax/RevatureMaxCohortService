package driver;

import config.JavalinConfig;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import config.ApiDatabaseConfig;

public class Main {
    public static void main(String[] args) {
//        ApiDatabaseConfig.initAssociates();
//        ApiDatabaseConfig.initEmployees();
        Javalin app = JavalinConfig.getApp(9001);
        FrontController fc = new FrontController(app);
    }
}

