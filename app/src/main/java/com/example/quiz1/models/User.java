package com.example.quiz1.models;

public class User {
    private int id;
    private String emailAddress;
    private String username;
    private String phoneNum;
    private String password;

    public User(int id, String emailAddress, String username, String phoneNum, String password) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.username = username;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
