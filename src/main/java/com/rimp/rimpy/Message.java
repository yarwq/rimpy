package com.rimp.rimpy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"chats", "password"}) // <-- ВАЖНО
    private User user; //from who
    @ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonIgnoreProperties({"userList", "message"}) // тоже можно ограничить
    private Chat chat;
    public Message(String message, User user, Chat chat) {
        this.message = message;
        this.user = user;
        this.chat = chat;
    }

    public Message() {

    }
}
