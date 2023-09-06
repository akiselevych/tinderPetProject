package dao;

import models.SessionUser;
import models.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LocalMemoUsersDao implements UsersDao {
    private List<User> users = List.of(
            new User(1L, "Polinka Lyubimka", "http://andreeanati.com/wp-content/uploads/2020/09/Mob-Journal-Prom-2.jpg"),
            new User(2L, "Polinka Kyslytska", "http://andreeanati.com/wp-content/uploads/2020/09/Mob-Journal-Prom-4.jpg"),
            new SessionUser(3L, "Anton Kiselevych", "https://avatars.githubusercontent.com/u/140167030?v=4?s=400", 99)
    );

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User findById(Long id) throws AccountNotFoundException {
        Optional<User> user = users.stream().filter(u -> Objects.equals(u.getId(), id)).findAny();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new AccountNotFoundException("User not found");
        }
    }

    @Override
    public List<User> findAllLikedUsers(int sessionId) {
        try {
            return getSessionUser(sessionId).getLikedUsers();
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public SessionUser getSessionUser(int sessionId) throws AccountNotFoundException {
        Optional<User> sessionUser = this.users.stream().filter(u -> u instanceof SessionUser).filter(u -> ((SessionUser) u).getSessionId() == sessionId).findAny();
        if (sessionUser.isPresent()) {
            return (SessionUser) sessionUser.get();
        } else {
            throw new AccountNotFoundException("User not found");
        }
    }

    @Override
    public void addLikedProfileToLikedUserList(int sessionId, User user) {
        try {
            getSessionUser(sessionId).addLikedUser(user);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}
