package com.rimp.rimpy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class User {
    private String name;
    private String bio;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String avatar;
    private String password;
    private boolean verified =false;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> message;
    @ManyToMany(mappedBy = "userList")
    @JsonIgnore
    private List<Chat> chat;
    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "id: "+this.login+", name: "+this.name;
    }

    public User() {

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getAvatar() {
        return avatar;
    }
    public String getName() {
        return this.name;
    }
    public String getBio() {
        return this.bio;
    }
    public String getLogin() {
        return this.login;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void setBio(String newBio) {
        this.bio = newBio;
    }

    public void setVerified(boolean newVerified) {
        this.verified = newVerified;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }
}
