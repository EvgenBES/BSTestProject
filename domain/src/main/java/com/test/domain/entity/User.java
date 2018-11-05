package com.test.domain.entity;


public class User implements DomainModel {

    private String login;
    private int userId;

    public User(String login, int userId) {
        this.login = login;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public int getUserId() {
        return userId;
    }
}
