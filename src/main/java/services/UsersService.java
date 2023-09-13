package services;

import models.SessionUser;
import models.User;

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
}
