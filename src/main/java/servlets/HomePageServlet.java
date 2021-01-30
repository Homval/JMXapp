package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import accountServer.AccountServer;

import java.io.IOException;
import java.io.PrintWriter;

public class HomePageServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(HomePageServlet.class.getName());
    public static final String PAGE_URL = "/admin";
    private final AccountServer accountServer;

    public HomePageServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUserCount();

        logger.info("UsersLimit: {} UsersCount: {}", limit, count);

        PrintWriter out = resp.getWriter();
        out.println(limit);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
