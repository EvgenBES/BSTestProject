package com.test.domain.usecases;

import com.test.domain.entity.User;
import com.test.domain.entity.UserRegist;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRegistUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public UserRegistUseCase(UserRepository userRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


    public Observable<User> regist(String email, String password) {
        return userRepository
                .registUser(new UserRegist(email.split("@")[0], password))
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
