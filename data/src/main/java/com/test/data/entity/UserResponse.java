package com.test.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse implements DataModel{

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("userId")
    @Expose
    private Integer userId;

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public Integer getUserId() {
        return userId;
    }
}
