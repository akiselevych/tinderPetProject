package models;

import java.util.Date;

public class Message {
    private int id;
    private int fromUserId;
    private int chatId;
    private Date sentDate;
    private String text;

    public Message(int id, int fromUserId, int chatId, Date sentDate, String text) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.chatId = chatId;
        this.sentDate = sentDate;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
