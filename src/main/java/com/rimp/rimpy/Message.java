package com.rimp.rimpy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Long messageId;
    private String message; //message
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"chats", "password"})
    private User user; //from who
    @ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonIgnoreProperties({"userList", "message"})
    private Chat chat;
    public Message(String message, User user, Chat chat) {
        this.message = message;
        this.user = user;
        this.chat = chat;
    }

    public Message() {

    }
}
