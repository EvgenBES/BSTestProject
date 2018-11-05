package com.test.presentation.screeens.user.list;

public interface OnLoginFinishedListener {

    void onEmailError(int idErrorEmail);

    void onPasswordError(int idErrorPassword);

    void onSuccess(String login, String password);
}
