package step;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import step.controller.*;
import step.dao.*;
import step.services.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Hello world!
 *
 */
public class JettyRun
{
    public static void main( String[] args ) throws Exception {
        Server server = new Server(Integer.parseInt(System.getenv("PORT")));

        TemplateEngine templateEngine = new TemplateEngine();

        UsersDao usersDao = new JdbcUsersDao();
        UsersService usersService = new JdbcUsersService(usersDao);

        MessagesDao messagesDao = new JdbcMessagesDao();
        MessagesService messagesService = new JdbcMessagesService(messagesDao);

        ChatsDao chatsDao = new JdbcChatsDao(messagesDao, usersDao);
        ChatsService chatsService = new JdbcChatsService(chatsDao);



        ServletContextHandler handler = new ServletContextHandler();
        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);

        IndexServlet indexServlet = new IndexServlet(templateEngine, usersService);
        MessagesServlet messagesServlet = new MessagesServlet(templateEngine, messagesService, usersService, chatsService);
        UsersServlet usersServlet = new UsersServlet(templateEngine, usersService, chatsService);
        LikedUsersServlet likedUsersServlet = new LikedUsersServlet(templateEngine, usersService, chatsService);
        LoginServlet loginServlet = new LoginServlet(templateEngine);
        LogOutServlet logOutServlet = new LogOutServlet(templateEngine);
        RegistrationServlet registrationServlet = new RegistrationServlet(templateEngine, usersService);

        LoginFilter loginFilter = new LoginFilter(templateEngine, usersService);

        handler.addServlet(new ServletHolder(indexServlet), "/");
        handler.addServlet(new ServletHolder(usersServlet), "/users");
        handler.addServlet(new ServletHolder(likedUsersServlet), "/liked");
        handler.addServlet(new ServletHolder(loginServlet), "/login");
        handler.addServlet(new ServletHolder(logOutServlet), "/logout");
        handler.addServlet(new ServletHolder(registrationServlet), "/registration");
        handler.addServlet(new ServletHolder(messagesServlet), "/messages/*");

        handler.addFilter(new FilterHolder(loginFilter), "/*", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(CSSBootstrapServlet.class, "/css/bootstrap.min.css");
        handler.addServlet(JsBootstrapServlet.class, "/js/bootstrap.min.css");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
