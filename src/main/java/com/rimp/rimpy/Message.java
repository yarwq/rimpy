package com.rimp.rimpy;

import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String message; //message
    @ManyToOne
    private User user; //from who
    @ManyToOne
    private Chat chat;
    public Message(String message, User user, Chat chat) {
        this.message = message;
        this.user = user;
        this.chat = chat;
    }

    public Message() {

    }
}
