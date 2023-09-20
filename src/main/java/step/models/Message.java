package step.models;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private int fromUserId;
    private int chatId;
    private LocalDateTime sentDate;
    private String text;

    public Message(int id, int fromUserId, int chatId, LocalDateTime sentDate, String text) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.chatId = chatId;
        this.sentDate = sentDate;
        this.text = text;
    }

    public Message(int fromUserId, int chatId, LocalDateTime sentDate, String text) {
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

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
