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
        int showingUserIndex = (int) session.getAttribute(LoginFilter.SHOWING_USER_INDEX);
        int sessionUserId = (Integer) session.getAttribute(LoginFilter.SESSION_USER_ID);
        User showingUser = showingUsers.get(showingUserIndex);
        if (req.getParameter("option").equals("like")) {
            usersService.addLikedProfileToLikedUserList(sessionUserId, showingUser);
            chatsService.create(sessionUserId, showingUser.getId());
        }
        session.setAttribute(LoginFilter.SHOWING_USER_INDEX, showingUserIndex + 1);
        resp.sendRedirect("/users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        int showingUserIndex = (int) session.getAttribute(LoginFilter.SHOWING_USER_INDEX);
        if (showingUsers == null) {
            try {
                showingUsers = usersService.findUnLikedUsers((Integer) session.getAttribute(LoginFilter.SESSION_USER_ID));
            } catch (AccountNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (showingUserIndex == showingUsers.size()) {
            resp.sendRedirect("/liked");
            return;
        }

        User showingUser = showingUsers.get(showingUserIndex);
        Map<String, Object> params = Map.of(
                "user", showingUser
        );

        templateEngine.render("like-page.ftl", params, resp);
    }
}

