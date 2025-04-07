package com.rimp.rimpy;

public class Message {
    private String message; //message
    private User user; //from who
    private Chat chat;
    public Message(String message, User user, Chat chat) {
        this.message = message;
        this.user = user;
        this.chat = chat;
    }
}
