package controller;

import models.User;
import services.ChatsService;
import services.UsersService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UsersService usersService;
    private final ChatsService chatsService;
    private List<User> showingUsers = null;

    public UsersServlet(TemplateEngine templateEngine, UsersService usersService, ChatsService chatsService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
        this.chatsService = chatsService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int showingUserIndex = (Integer) session.getAttribute("showing-user-index");
        try {
            User showingUser = usersService.findUnLikedUsers(4).get(showingUserIndex);
            if (req.getParameter("option").equals("like")) {
                usersService.addLikedProfileToLikedUserList(4, showingUser);
                chatsService.create(4,showingUser.getId());
            }
            session.setAttribute("showing-user-index", showingUserIndex + 1);
            resp.sendRedirect("/users");
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (showingUsers == null) {
            try {
                showingUsers = usersService.findUnLikedUsers(4);
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if ((Integer) session.getAttribute("showing-user-index") == showingUsers.size()) {
            resp.sendRedirect("/liked");
            return;
        }

        User showingUser = showingUsers.get((Integer) session.getAttribute("showing-user-index"));
        Map<String, Object> params = Map.of(
                "user", showingUser
        );

        templateEngine.render("like-page.ftl", params, resp);
    }
}

