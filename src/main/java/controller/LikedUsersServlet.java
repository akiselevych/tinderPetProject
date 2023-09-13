package controller;

import services.ChatsService;
import services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LikedUsersServlet extends HttpServlet {
    private  final TemplateEngine templateEngine;
    private final UsersService usersService;
    private final ChatsService chatsService;

    public LikedUsersServlet(TemplateEngine templateEngine, UsersService usersService, ChatsService chatsService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
        this.chatsService = chatsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int sessionUserId = (int) session.getAttribute(LoginFilter.SESSION_USER_ID);
        Map<String, Object> params = Map.of(
                "users", usersService.findAllLikedUsers(sessionUserId),
                "chats", chatsService.findAllSessionUserChats(sessionUserId)
        );
        templateEngine.render("people-list.ftl", params,resp);
    }
}
