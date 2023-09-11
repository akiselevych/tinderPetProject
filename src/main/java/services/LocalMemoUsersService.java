package services;

import dao.UsersDao;
import models.SessionUser;
import models.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public class LocalMemoUsersService implements UsersService{
    private final UsersDao usersDao;

    public LocalMemoUsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public List<User> findAll() {
        return usersDao.findAll();
    }

    @Override
    public List<User> findUnLikedUsers(Long sessionUserid) throws AccountNotFoundException {
        return null;
    }

    @Override
    public User findById(Long id) throws AccountNotFoundException {
        return usersDao.findById(id);
    }

    @Override
    public List<User> findAllLikedUsers(Long sessionId) {
        return usersDao.findAllLikedUsers(sessionId);
    }

    @Override
    public SessionUser getSessionUser(Long sessionId) throws AccountNotFoundException {
        return usersDao.getSessionUser(sessionId);
    }

    @Override
    public void addLikedProfileToLikedUserList(Long sessionId, User user) {
        usersDao.addLikedProfileToLikedUserList(sessionId, user);
    }
}
