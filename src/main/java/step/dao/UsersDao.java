package step.dao;

import step.models.SessionUser;
import step.models.User;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
    List<User> findAll();
    User findById(int id) throws AccountNotFoundException;
    List<User> findAllLikedUsers(int userId);

    SessionUser getSessionUser(int sessionId) throws AccountNotFoundException;

    void addLikedProfileToLikedUserList(int sessionUserId, User user);

    default Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://ep-weathered-voice-67196930-pooler.eu-central-1.postgres.vercel-storage.com:5432/verceldb", "default", "2KbscPtoxDv5");
    }

    SessionUser findUserByLoginPassword(String login, String password) throws AccountNotFoundException;

    User createNewUser(String name, String avatarUrl, String gender, String login, String password) throws AccountException;
}
