package controller;

import Utils.Converting;
import services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LikedUsersServlet extends HttpServlet {
    private  final TemplateEngine templateEngine;
    private final UsersService usersService;

    public LikedUsersServlet(TemplateEngine templateEngine, UsersService usersService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> params = Map.of(
                "users", usersService.findAllLikedUsers((Integer) req.getSession().getAttribute("session-id"))
        );
        templateEngine.render("/people-list.ftl", params,resp);
    }
}
