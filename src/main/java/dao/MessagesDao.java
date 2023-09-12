package dao;

import models.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface MessagesDao {
    List<Message> findAllChatMessages(int chatId);
    Message findMessageById(int messageId);
    void sendMessage(int chatId, Message message);
    default Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://ep-weathered-voice-67196930-pooler.eu-central-1.postgres.vercel-storage.com:5432/verceldb", "default", "2KbscPtoxDv5");
    }
}
