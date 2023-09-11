import controller.*;
import dao.JdbcUsersDao;
import dao.UsersDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import services.JdbcUsersService;
import services.UsersService;

/**
 * Hello world!
 *
 */
public class JettyRun
{
    public static void main( String[] args ) throws Exception {
        Server server = new Server(8081);

        TemplateEngine templateEngine = new TemplateEngine();

        UsersDao usersDao = new JdbcUsersDao();
        UsersService usersService = new JdbcUsersService(usersDao);

        ServletContextHandler handler = new ServletContextHandler();
        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);
        IndexServlet indexServlet = new IndexServlet(templateEngine);
        MessagesServlet messagesServlet = new MessagesServlet(templateEngine);
        UsersServlet usersServlet = new UsersServlet(templateEngine, usersService);
        LikedUsersServlet likedUsersServlet = new LikedUsersServlet(templateEngine, usersService);

        handler.addServlet(new ServletHolder(indexServlet), "/");
        handler.addServlet(new ServletHolder(usersServlet), "/users");
        handler.addServlet(new ServletHolder(likedUsersServlet), "/liked");
        handler.addServlet(new ServletHolder(messagesServlet), "/messages/1");

        handler.addServlet(CSSBootstrapServlet.class, "/css/bootstrap.min.css");
        handler.addServlet(JsBootstrapServlet.class, "/js/bootstrap.min.css");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
