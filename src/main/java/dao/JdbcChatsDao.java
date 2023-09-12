package dao;

import models.Chat;
import models.Message;

import javax.management.InstanceNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcChatsDao implements ChatsDao{
    private MessagesDao messagesDao;
    private UsersDao usersDao;

    public JdbcChatsDao(MessagesDao messagesDao, UsersDao usersDao) {
        this.messagesDao = messagesDao;
        this.usersDao = usersDao;
    }

    @Override
    public List<Chat> findAllSessionUserChats(int userId) {
        List<Chat> chats = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("""
                    SELECT * FROM chats c  WHERE ? = ANY(c.participants_id)
                    """);
            ps.setInt(1,userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                List<Message> messages = messagesDao.findAllChatMessages(id);
                Integer[] participantsId = (Integer[]) resultSet.getArray("participants_id").getArray();
                chats.add(new Chat(id,messages,participantsId));
            }
            return chats;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Chat findChatById(int id) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM chats WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int chatId = resultSet.getInt("id");
                List<Message> messages = messagesDao.findAllChatMessages(id);
                Integer[] participantsId = (Integer[]) resultSet.getArray("participants_id").getArray();
                return new Chat(chatId,messages,participantsId);
            }
            throw new InstanceNotFoundException("No chat found");
        } catch (SQLException | InstanceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(int sessionUserId, int likedUserId) {
        try(Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(" INSERT INTO chats (participants_id) VALUES (ARRAY[?,?]) ");
            statement.setInt(1,sessionUserId);
            statement.setInt(2,likedUserId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
