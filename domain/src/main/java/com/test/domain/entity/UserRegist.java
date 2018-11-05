package com.test.domain.entity;

public class UserRegist implements DomainModel {

    private String login;
    private String password;

    public UserRegist(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
