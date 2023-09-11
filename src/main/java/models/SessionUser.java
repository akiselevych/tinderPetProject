package models;

import java.util.ArrayList;
import java.util.List;

public class SessionUser extends User{
    private final List<User> likedUsers;
    private final Integer sessionId;

    public SessionUser(Integer id, String name, String avatarUrl, String gender, int sessionId) {
        super(id, name, avatarUrl, gender);
        this.likedUsers = new ArrayList<>();
        this.sessionId = sessionId;
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public int getSessionId() {
        return sessionId;
    }
    public void addLikedUser(User user) {
        this.likedUsers.add(user);
    }
}
