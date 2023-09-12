package services;

import dao.MessagesDao;
import models.Message;

import java.util.List;

public class JdbcMessagesService implements MessagesService{
    private MessagesDao messagesDao;

    public JdbcMessagesService(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    @Override
    public List<Message> findAllChatMessages(int chatId) {
        return messagesDao.findAllChatMessages(chatId);
    }

    @Override
    public Message findMessageById(int messageId) {
        return messagesDao.findMessageById(messageId);
    }

    @Override
    public void sendMessage(int chatId, Message message) {
        messagesDao.sendMessage(chatId, message);
    }
}
