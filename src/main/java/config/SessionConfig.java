package config;

import java.io.File;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

public class SessionConfig {
    public static SessionHandler fileSessionHandler() {
        SessionHandler sessionHandler = new SessionHandler();
        SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
        sessionCache.setSessionDataStore(fileSessionDataStore());
        sessionHandler.setSessionCache(sessionCache);
        sessionHandler.setHttpOnly(true);
        return sessionHandler;
    }

    public static FileSessionDataStore fileSessionDataStore() {
        FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        File storeDir = new File(baseDir, "javalin-session-store");
        storeDir.mkdir();
        fileSessionDataStore.setStoreDir(storeDir);
        return fileSessionDataStore;
    }
}
