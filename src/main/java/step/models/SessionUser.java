package step.models;

import java.util.List;

public class SessionUser extends User{
    private final Integer sessionId;
    private List<Chat> chats;

    public SessionUser(Integer id, String name, String avatarUrl, String gender, int sessionId) {
        super(id, name, avatarUrl, gender);
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }
}
