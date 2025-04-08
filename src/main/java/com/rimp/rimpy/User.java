package com.rimp.rimpy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Random;

@Entity
public class User {
    private String name;
    private String bio;
    @Id
    private String login;
    private String password;
    private boolean verified =false;

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
}
