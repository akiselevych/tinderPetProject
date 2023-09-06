package controller;

import Utils.Converting;
import models.SessionUser;
import models.User;
import services.UsersService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UsersService usersService;

    public UsersServlet(TemplateEngine templateEngine, UsersService usersService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Long showingUserId = Converting.convertToLong(session.getAttribute("showingUserId"));
        try {
            SessionUser sessionUser = null;
            if (usersService.findById(showingUserId) instanceof SessionUser){
                sessionUser = (SessionUser) usersService.findById(showingUserId);
            }
            User showingUser = usersService.findById(showingUserId);
            if (req.getParameter("option").equals("like")) {
                usersService.addLikedProfileToLikedUserList((int) session.getAttribute("session-id"), showingUser);
            }
            if (sessionUser != null && sessionUser.equals(showingUser)) {
                session.setAttribute("showingUserId", showingUserId + 2);
            } else {
                session.setAttribute("showingUserId", showingUserId + 1);
            }
            resp.sendRedirect("/users");
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
//            resp.sendRedirect("/dashboard");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if (session.getAttribute("session-id") == null) {
            session.setAttribute("session-id", 99);
        }
        if (session.getAttribute("showingUserId") == null) {
            session.setAttribute("showingUserId", 1);
        }
        try {
            User userById = usersService.findById(Converting.convertToLong(session.getAttribute("showingUserId")));
            if (usersService.findAll().indexOf(userById) == usersService.findAll().size() - 1) {
                resp.sendRedirect("/liked");
            }
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            User userById = usersService.findById(Converting.convertToLong(session.getAttribute("showingUserId")));
            Map<String, Object> params = Map.of(
                    "user", userById
            );

            templateEngine.render("like-page.ftl", params, resp);
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
