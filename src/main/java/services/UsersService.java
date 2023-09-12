package services;

import models.SessionUser;
import models.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> findAll();
    List<User> findUnLikedUsers(int sessionUserid) throws AccountNotFoundException;
    User findById(int id) throws AccountNotFoundException;
    List<User> findAllLikedUsers(int sessionUserid);

    SessionUser getSessionUser(int sessionId) throws AccountNotFoundException;

    void addLikedProfileToLikedUserList(int sessionUserId, User user);

    User findUserByLoginPassword(String login, String password) throws AccountNotFoundException;
}
