package controller;

import models.User;
import services.UsersService;

import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {
    TemplateEngine templateEngine;
    UsersService usersService;

    public RegistrationServlet(TemplateEngine templateEngine, UsersService usersService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine.render("registration.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String avatarUrl = req.getParameter("avatar_url");
        String gender = req.getParameter("gender");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = usersService.createNewUser(name, avatarUrl, gender, login, password);
            if (user.getId() != null) {
                Map<String, Object> params = Map.of(
                        "completed", true
                );
                templateEngine.render("login.ftl", params,resp);
            }
        } catch (AccountException e) {
            Map<String, Object> params = Map.of(
                    "error", e.getMessage()
            );
            templateEngine.render("registration.ftl", params,resp);
            e.printStackTrace();
        }
    }
}
