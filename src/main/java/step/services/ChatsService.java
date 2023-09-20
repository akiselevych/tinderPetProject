package step.services;

import step.models.Chat;

import java.util.List;

public interface ChatsService {
    List<Chat> findAllSessionUserChats(int userId);

    Chat findChatById(int id);
    void create(int sessionUserId, int likedUserId);
}
