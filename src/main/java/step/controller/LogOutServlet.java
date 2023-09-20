package step.controller;

import step.Utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LogOutServlet extends HttpServlet {
    TemplateEngine templateEngine;

    public LogOutServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> sessionId = CookieUtil.findCookieByName(req, LoginFilter.SESSION_ID);
        req.getSession(false).invalidate();
        sessionId.ifPresent(c -> {
            c.setMaxAge(0);
            c.setPath("/");
            resp.addCookie(c);
        });
        templateEngine.render("login.ftl", resp);
    }
}
