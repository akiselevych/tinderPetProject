package step.services;

import step.models.SessionUser;
import step.models.User;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface UsersService {
    List<User> findAll();
    List<User> findUnLikedUsers(int sessionUserid) throws AccountNotFoundException;
    User findById(int id) throws AccountNotFoundException;
    List<User> findAllLikedUsers(int sessionUserid);

    SessionUser getSessionUser(int sessionId) throws AccountNotFoundException;

    void addLikedProfileToLikedUserList(int sessionUserId, User user);

    SessionUser findUserByLoginPassword(String login, String password) throws AccountNotFoundException;
    User createNewUser(String name, String avatarUrl, String gender, String login, String password) throws AccountException;
}
