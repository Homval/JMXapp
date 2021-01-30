package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import accountServer.AccountServer;
import accountServer.AccountServerController;
import accountServer.AccountServerControllerMBean;
import accountServer.AccountServerImpl;
import servlets.HomePageServlet;
import servlets.ResourceServlet;
import resourceService.ResourceService;
import resourceService.ResourceServiceMBean;
import resources.TestResource;

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

        final int port = 8080;

        logger.info("Starting at localhost:" + port);

        AccountServer accountServer = new AccountServerImpl(10);
        TestResource testResource = new TestResource();

        // JMX creating

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController");
        mbs.registerMBean(serverStatistics, name);

        ResourceServiceMBean resourceService = new ResourceService(testResource);
        ObjectName name1 = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resourceService, name1);


        // Start server

        Server server = new Server(port);

        // Servlet context creating
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new ResourceServlet(testResource)), ResourceServlet.RESOURCE_URL);

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
