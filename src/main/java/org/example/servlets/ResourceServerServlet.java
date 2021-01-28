package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sax.ReadXMLFileSAX;
import org.example.resource.TestResource;

import java.io.IOException;

public class ResourceServerServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(ResourceServerServlet.class.getName());
    public static final String RESOURCE_URL = "/resource";
    private final TestResource testResource;

    public ResourceServerServlet(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathToResource = req.getParameter("path");
        logger.info("Path to resource: " + pathToResource);
        TestResource newResource = (TestResource) ReadXMLFileSAX.readXML(pathToResource);
        testResource.setName(newResource.getName());
        testResource.setAge(newResource.getAge());
        logger.info("Parsing complete");
    }
}
