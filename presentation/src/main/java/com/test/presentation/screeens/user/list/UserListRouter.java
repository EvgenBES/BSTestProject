package com.test.presentation.screeens.user.list;

import android.os.Handler;
import android.test.com.testproject.R;
import android.text.TextUtils;

import com.test.presentation.base.BaseRouter;

import java.util.List;

public class UserListRouter extends BaseRouter<UserListActivity> {

    public UserListRouter(UserListActivity activity) {
        super(activity);
    }

    public void setErrorEmail(String errorEmail) {
        activity.setErrorEmail(errorEmail);
    }

    public void setErrorPassword(String errorPassword) {
        activity.setErrorPassword(errorPassword);
    }


    public void login(final String email, final String password, final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(() -> {
            //validation email
            if (TextUtils.isEmpty(email)) {
                listener.onEmailError(R.string.email_empty);
                return;
            }

            if (!email.isEmpty()) {
                if (email.startsWith("@")) {
                    listener.onEmailError(R.string.email_start_dog);
                    return;
                }
            }

            if (!email.isEmpty()) {
                if (email.endsWith("@")) {
                    listener.onEmailError(R.string.email_end_dog);
                    return;
                }
            }

            if (!email.isEmpty()) {
                if (!email.contains("@")) {
                    listener.onEmailError(R.string.email_empty_dog);
                    return;
                }
            }

            if (!email.isEmpty()) {
                String simDog = "@";
                char chSim = simDog.charAt(0);
                int count = 0;
                for (int i = 0; i < email.length(); i++) {
                    if (email.charAt(i) == chSim) {
                        count++;
                    }
                    if (count >= 2) {
                        listener.onEmailError(R.string.email_not_correct);
                        return;
                    }
                }
            }


            //validation password
            if (TextUtils.isEmpty(password)) {
                listener.onPasswordError(R.string.password_empty);
                return;
            }
            if (password.length() > 20 || password.length() < 8) {
                listener.onPasswordError(R.string.password_long);
                return;
            }

            listener.onSuccess(email, password);
        }, 2000);
    }



}
