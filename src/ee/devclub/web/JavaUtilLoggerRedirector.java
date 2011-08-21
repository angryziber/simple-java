package ee.devclub.web;

import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JavaUtilLoggerRedirector implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // remove default handlers
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }
//        install redirecting handler
        SLF4JBridgeHandler.install();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
