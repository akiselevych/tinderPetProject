package models;

import java.util.Date;

public class Message {
    private int id;
    private int fromUserId;
    private int toUserId;
    private Date sentDate;
    private String text;

    public Message(int id, int fromUserId, int toUserId, Date sentDate, String text) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
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

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
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
