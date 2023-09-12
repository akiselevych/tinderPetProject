package dao;

import models.Message;

import javax.management.InstanceNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMessagesDao implements MessagesDao {
    @Override
    public List<Message> findAllChatMessages(int chatId) {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE chat_id =?");
            statement.setInt(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getInt("from_user_id"),
                        resultSet.getInt("chat_id"),
                        resultSet.getDate("sent_date"),
                        resultSet.getString("text")
                ));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message findMessageById(int messageId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE chat_id =?");
            statement.setInt(1, messageId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Message(
                        resultSet.getInt("id"),
                        resultSet.getInt("from_user_id"),
                        resultSet.getInt("chat_id"),
                        resultSet.getDate("sent_date"),
                        resultSet.getString("text")
                );
            }
            throw new InstanceNotFoundException("Message not found");
        } catch (SQLException | InstanceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(int chatId, Message message) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO messages(from_user_id,sent_date,text,chat_id)
                    VALUES(?,?,?,?)
                    """);
            statement.setInt(1, message.getFromUserId());
            statement.setDate(2, new Date(message.getSentDate().getTime()));
            statement.setString(3, message.getText());
            statement.setInt(4, chatId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
