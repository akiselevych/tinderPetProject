package controller;


import models.User;
import services.UsersService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class IndexServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private UsersService usersService;

    public IndexServlet(TemplateEngine templateEngine, UsersService usersService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int sessionUserId = (int) request.getSession(false).getAttribute(LoginFilter.SESSION_USER_ID);
            User user = usersService.findById(sessionUserId);
            Map<String, Object> params = Map.of(
                    "sessionUserName",user.getName()
                    );
            templateEngine.render("dashboard.ftl", params,response);
        } catch (AccountNotFoundException e) {
            response.sendRedirect("/logout");
        }
    }
}
