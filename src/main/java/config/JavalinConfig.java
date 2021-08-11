package config;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.eclipse.jetty.server.Server;

public class JavalinConfig {
//    /**
//     * Generate a Javalin instance with some preconfigured options
//     *
//     * enables CORS for 127.0.0.1
//     * sets /css, /img, /javascript and /
//     *      as avenues for finding static files
//     *      the last one being our html folder
//     *
//     * Starts the server on the port provided
//     * @param port
//     * @return
//     */
    public static Javalin getApp(int port) {
        Javalin app = Javalin.create(config -> config.sessionHandler(SessionConfig::fileSessionHandler)
                .enableCorsForAllOrigins()
                .server(()->{
                    // configure https/ssl if needed
                    return new Server();
                })).start(port);

        LoggerConfig.log(JavalinConfig.class.getSimpleName(), "Javalin started");

        return app;
    }
}
