package org.example.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.accountServer.AccountServer;
import org.example.accountServer.AccountServerController;
import org.example.accountServer.AccountServerControllerMBean;
import org.example.accountServer.AccountServerImpl;
import org.example.servlets.HomePageServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Hello world!
 *
 */
public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main( String[] args ) throws Exception {
        if (args.length != 1) {
            logger.error("Use port as first argument");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        logger.info("Starting at localhost:" + port);

        AccountServer accountServer = new AccountServerImpl(10);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStatistics, name);

        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("C:\\Users\\in00\\IdeaProjects\\JMXapp\\src\\main\\resources");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resourceHandler, context});
        server.setHandler(handlers);

        server.start();
        logger.info("Server started");
        server.join();

    }
}
