package com.rimp.rimpy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messageList;
    @ManyToMany
    @JsonIgnore
    private List<User> userList;
    @ManyToOne
    @JsonIgnore
    private User user;
    private String name;
}
