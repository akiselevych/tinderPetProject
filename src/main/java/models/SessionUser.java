package models;

import java.util.ArrayList;
import java.util.List;

public class SessionUser extends User{
    private final List<User> likedUsers;
    private final int sessionId;
    public SessionUser(Long id, String name, String avatarUrl, int sessionId) {
        super(id, name, avatarUrl);
        this.sessionId = sessionId;
        this.likedUsers = new ArrayList<>();
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
