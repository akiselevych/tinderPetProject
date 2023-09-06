package services;

import models.SessionUser;
import models.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> findAll();
    User findById(Long id) throws AccountNotFoundException;
    List<User> findAllLikedUsers(int sessionId);

    SessionUser getSessionUser(int sessionId) throws AccountNotFoundException;

    void addLikedProfileToLikedUserList(int sessionId, User user);
}
