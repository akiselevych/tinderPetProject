package controller;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private List<User> users;
    private int count = 0;

    public UsersServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        users = List.of(
                new User("Barbara Monika", "https://media.glamourmagazine.co.uk/photos/643911c5faffaaf0fce7d598/1:1/w_1280,h_1280,c_limit/SOFT%20GIRL%20AESTHETIC%20140423%20rachelteetyler_L.jpeg"),
                new User("Alegina Fanta", "https://cosmeticsbusiness.com/article-image-alias/vanilla-girl-beauty-is-already-the.jpeg")
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("option"));
        resp.sendRedirect("/users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> params = Map.of(
                "user", users.get(count)
        );
        templateEngine.render("like-page.ftl", params,resp);
        count++;
    }
}
