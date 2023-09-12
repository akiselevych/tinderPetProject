package controller;

import Utils.CookieUtil;
import services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private UsersService userService;

    public LoginServlet(TemplateEngine templateEngine, UsersService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Optional<Cookie> optionalCookie = CookieUtil.findCookieByName(req, "session-id");
        if (optionalCookie.isEmpty()) {

        }
        if (session.getAttribute("session-id") == null && session.getAttribute("showing-user-index") == null) {
            session.setAttribute("session-id", 4);
            session.setAttribute("session-user-id", 4);
            session.setAttribute("showing-user-index", 0);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
