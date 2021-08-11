package config;

import java.time.LocalDateTime;

import io.javalin.Javalin;

public class LoggerConfig {
    /**
     * The levels at which logging is available for Javalin's underlying implementation
     * By default, we use Level.INFO.
     * Ordered by least restrictive to most: TRACE, DEBUG, INFO, WARN, ERROR, OFF
     */
    enum Level {TRACE, DEBUG, INFO, WARN, ERROR, OFF};
    private static Level level = Level.INFO;
    private static final String formatter = "{} {} :: {}"; // datetime classname :: message

    public static void setLoggerLevel(Level logLevel) {
        level = logLevel;
    }
    
    /**
     * Set a log at the currently defined level with the current local time.
     * 
     * @param cls The class it occurred in
     * @param message The accompanying message
     */
    public static void log(String cls, String message) {
        log(LocalDateTime.now(), cls, message);
    }

    /**
     * Set a log at the currently defined level.
     * 
     * @param ldt The time the event occurred
     * @param cls The class it occurred in
     * @param message The accompanying message
     */
    public static void log(LocalDateTime ldt, String cls, String message) {
        if (level != Level.OFF) {
            switch (level) {
                case TRACE:
                    Javalin.log.trace(formatter, ldt, cls, message);
                    break;
                case DEBUG:
                    Javalin.log.debug(formatter, ldt, cls, message);
                    break;
                case INFO:
                    Javalin.log.info(formatter, ldt, cls, message);
                    break;
                case WARN:
                    Javalin.log.warn(formatter, ldt, cls, message);
                    break;
                case ERROR:
                    Javalin.log.error(formatter, ldt, cls, message);
                    break;
                case OFF:
                    break;
            }
        }
    }
}
