package services;

import dao.UsersDao;
import models.SessionUser;
import models.User;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JdbcUsersService implements UsersService{
    private UsersDao usersDao;

    public JdbcUsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public List<User> findAll() {
        return usersDao.findAll();
    }

    @Override
    public List<User> findUnLikedUsers(int sessionUserid) throws AccountNotFoundException {
        List<User> allUsers = findAll();
        List<User> likedUsers = findAllLikedUsers(sessionUserid);
        Optional<User> sessionUser = allUsers.stream().filter(u -> u.getId() == sessionUserid).findAny();
        if (sessionUser.isPresent()) {
            allUsers.remove(sessionUser.get());
            allUsers.removeAll(likedUsers);
            return allUsers.stream().filter(u -> !Objects.equals(u.getGender(), sessionUser.get().getGender())).toList();
        } else {
            throw new AccountNotFoundException("Session not found");
        }
    }

    @Override
    public User findById(int id) throws AccountNotFoundException {
        return usersDao.findById(id);
    }

    @Override
    public List<User> findAllLikedUsers(int sessionUserid) {
        return usersDao.findAllLikedUsers(sessionUserid);
    }

    @Override
    public SessionUser getSessionUser(int sessionId) throws AccountNotFoundException {
        return usersDao.getSessionUser(sessionId);
    }

    @Override
    public void addLikedProfileToLikedUserList(int sessionId, User user) {
        usersDao.addLikedProfileToLikedUserList(sessionId, user);
    }
    @Override
    public SessionUser findUserByLoginPassword(String login, String password) throws AccountNotFoundException {
        return usersDao.findUserByLoginPassword(login, password);
    }

    @Override
    public User createNewUser(String name, String avatarUrl, String gender, String login, String password) throws AccountException {
        return usersDao.createNewUser(name, avatarUrl, gender, login, password);
    }
}
