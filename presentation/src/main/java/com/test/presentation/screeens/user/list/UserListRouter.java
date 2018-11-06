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

    public void closeKeyboard() {
      activity.closeKeyboard();
    }

}
