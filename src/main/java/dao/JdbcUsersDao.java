package dao;

import models.SessionUser;
import models.User;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUsersDao implements UsersDao{
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String avatarUrl = resultSet.getString("avatar_url");
                String gender = resultSet.getString("gender");
                users.add(new User(id,name,avatarUrl,gender));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findById(int id) throws AccountNotFoundException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id =?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String avatarUrl = resultSet.getString("avatar_url");
                String gender = resultSet.getString("gender");
                return new User(userId,name,avatarUrl,gender);
            }
            throw new AccountNotFoundException("Account not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllLikedUsers(int userId) {
        List<User> likedUsers = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT liked_users FROM users WHERE id = ?");
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Array column = resultSet.getArray("liked_users");
                Integer[] ids = (Integer[]) column.getArray();
                for(Integer id : ids){
                    statement = connection.prepareStatement("SELECT name,avatar_url,gender FROM users WHERE id = ?");
                    statement.setInt(1,id);
                    resultSet = statement.executeQuery();
                    if(resultSet.next()){
                        String name = resultSet.getString("name");
                        String avatarUrl = resultSet.getString("avatar_url");
                        String gender = resultSet.getString("gender");
                        likedUsers.add(new User(id,name,avatarUrl,gender));
                    }
                }
            }
            return likedUsers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SessionUser getSessionUser(int sessionId) throws AccountNotFoundException {
        try(Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("""
                    SELECT u.id, u.name, u.avatar_url, u.gender, c.id, c.messages_id, c.participants_id
                    FROM users u
                    INNER JOIN sessions s ON u.id = s.user_id
                    LEFT JOIN chats c ON u.id = ANY(c.participants_id)
                    WHERE s.id = ?
                    """);
            ps.setInt(1, sessionId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Integer userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String avatarUrl = resultSet.getString("avatar_url");
                String gender = resultSet.getString("gender");
                return new SessionUser(userId, name, avatarUrl, gender, sessionId);
            }
            throw new AccountNotFoundException("Session not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void addLikedProfileToLikedUserList(int sessionUserId, User user) {
        try(Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("""
                    UPDATE users
                    SET liked_users = array_append(liked_users, ?)
                    WHERE id = ?
                    """);
            ps.setInt(1, user.getId());
            ps.setInt(2, sessionUserId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
