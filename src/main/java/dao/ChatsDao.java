package dao;

import models.Chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface ChatsDao {
    List<Chat> findAllSessionUserChats(int userId);

    Chat findChatById(int id);
    void create(int sessionUserId, int likedUserId);


    default Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://ep-weathered-voice-67196930-pooler.eu-central-1.postgres.vercel-storage.com:5432/verceldb", "default", "2KbscPtoxDv5");
    }
}