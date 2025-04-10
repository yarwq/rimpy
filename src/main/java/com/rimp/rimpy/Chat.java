package com.rimp.rimpy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Message> messageList;
    @ManyToMany
    private List<User> userList;
    @ManyToOne
    private User user;
    private String name;
}
