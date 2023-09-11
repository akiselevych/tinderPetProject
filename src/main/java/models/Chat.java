package models;

import java.util.List;

public class Chat {
    private int id;
    private List<Message> messages;
    private Integer[] participants;

    public Chat(int id, List<Message> messages, Integer[] participants) {
        this.id = id;
        this.messages = messages;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Integer[] getParticipants() {
        return participants;
    }

    public void setParticipants(Integer[] participants) {
        this.participants = participants;
    }
}
