package com.test.presentation.screeens.user.list;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.test.com.testproject.R;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.test.app.App;
import com.test.domain.entity.User;
import com.test.domain.usecases.UserRegistUseCase;
import com.test.presentation.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class UserListViewModel extends BaseViewModel<UserListRouter, User> implements OnLoginFinishedListener {

    public ObservableInt peopleProgress;
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");


    @Inject
    public UserRegistUseCase userRegistUseCase;


    private List<String> domain = Arrays.asList("gmail.com", "mail.ru", "tut.by", "yahoo.com", "yandex.ru", "qip.ru", "gmail.co.uk");


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public UserListViewModel() {
        peopleProgress = new ObservableInt(View.GONE);

    }

    @Override
    public void setItem(User item) {

    }


    @Override
    public void onEmailError(int idErrorEmail) {
        peopleProgress.set(View.GONE);
        router.setErrorEmail(router.getActivity().getString(idErrorEmail));
    }

    @Override
    public void onPasswordError(int idErrorPassword) {
        peopleProgress.set(View.GONE);
        router.setErrorPassword(router.getActivity().getString(idErrorPassword));
    }

    @Override
    public void onSuccess(String login, String password) {
        peopleProgress.set(View.GONE);

        userRegistUseCase
                .regist(login, password)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!e.getMessage().contains("Attempt")) {
                            router.setErrorEmail(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void onClickButtonRegist() {
        peopleProgress.set(View.VISIBLE);
        login(email.get(), password.get(), this);
        router.closeKeyboard();
    }


    public List<String> getListDomain(String s) {
        List<String> list = new ArrayList<>();
        int simbol = s.indexOf("@");
        String prefix;
        if (simbol == -1) {
            prefix = s;
        } else {
            prefix = s.substring(0, simbol);
        }

        for (String domains : domain) {
            String addDomain = prefix + "@" + domains;
            if (addDomain.startsWith(s)) {
                list.add(addDomain);
            }
        }
        return list;
    }


    private void login(final String email, final String password, final OnLoginFinishedListener listener) {
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

            if (!email.isEmpty()) {
                String[] splitEmail = email.split("@", email.length());
                for (int i = 0; i < domain.size(); i++) {
                    if (domain.get(i).equals(splitEmail[1])) {
                        break;
                    } else if (i >= domain.size() - 1 && !domain.get(i).equals(splitEmail[1])) {
                        listener.onEmailError(R.string.email_domain_error);
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
        }, 1000);
    }

}


