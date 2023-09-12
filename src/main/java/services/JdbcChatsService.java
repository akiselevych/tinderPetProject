package services;

import dao.ChatsDao;
import models.Chat;

import java.util.List;

public class JdbcChatsService implements ChatsService {
    private ChatsDao chatsDao;

    public JdbcChatsService(ChatsDao chatsDao) {
        this.chatsDao = chatsDao;
    }

    @Override
    public List<Chat> findAllSessionUserChats(int userId) {
        return chatsDao.findAllSessionUserChats(userId);
    }

    @Override
    public Chat findChatById(int id) {
        return chatsDao.findChatById(id);
    }

    @Override
    public void create(int sessionUserId, int likedUserId) {
        chatsDao.create(sessionUserId, likedUserId);
    }
}
