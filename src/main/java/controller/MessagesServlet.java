package controller;

import models.Chat;
import models.Message;
import models.User;
import services.ChatsService;
import services.MessagesService;
import services.UsersService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MessagesServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private MessagesService messagesService;
    private UsersService usersService;
    private ChatsService chatsService;
    public MessagesServlet(TemplateEngine templateEngine, MessagesService messagesService, UsersService usersService, ChatsService chatsService) {
        this.templateEngine = templateEngine;
        this.messagesService = messagesService;
        this.usersService = usersService;
        this.chatsService = chatsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        Integer sessionUserId = (Integer) req.getSession().getAttribute("session-user-id");
        if (path != null) {
            String[] pathParts = path.split("/");
            if (pathParts.length >= 2) {
                int id = Integer.parseInt(pathParts[1]);
                try {
                    Chat chat = chatsService.findChatById(id);
                    User sessionUser = usersService.findById(sessionUserId);
                    User secondUser = usersService.findById(sessionUserId == chat.getParticipants()[0] ? chat.getParticipants()[1] : chat.getParticipants()[0]);
                    List<Message> messages = messagesService.findAllChatMessages(id);
                    Map<String, Object> params = Map.of(
                            "user", secondUser,
                            "sessionUser", sessionUser,
                            "messages", messages,
                            "chat",chat
                    );
                    templateEngine.render("./chat.ftl", params,resp);
                } catch (AccountNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int chatId = Integer.parseInt(req.getPathInfo().split("/")[1]);
        Integer sessionUserId = (Integer) req.getSession().getAttribute("session-user-id");
        if (!req.getParameter("message").isEmpty()) {
            messagesService.sendMessage(chatId, new Message(sessionUserId, chatId, LocalDateTime.now(),req.getParameter("message")));
            resp.sendRedirect("/messages/" + chatId);
        }
    }
}
