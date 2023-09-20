package step.services;

import step.models.Message;

import java.util.List;

public interface MessagesService {
    List<Message> findAllChatMessages(int chatId);
    Message findMessageById(int messageId);
    void sendMessage(int chatId, Message message);
}
