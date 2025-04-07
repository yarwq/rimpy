package com.rimp.rimpy;


public class User {
    private String name;
    private String bio;
    private String login;
    private String password;
    private boolean verified =false;

    public User(String name, String bio , String login, String password) {
        this.name = name;
        this.bio = bio;
        this.login = login;
        this.password = password;
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
}
