package com.test.data.repositories;

import com.test.data.entity.UserResponse;
import com.test.data.net.RestService;
import com.test.domain.entity.User;
import com.test.domain.entity.UserRegist;
import com.test.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    private RestService restService;

    @Inject
    public UserRepositoryImpl(RestService restService) {
        this.restService = restService;
    }


    @Override
    public Observable<User> registUser(UserRegist user) {
        return restService
                .registUser(user)
                .map(new Function<UserResponse, User>() {
                    @Override
                    public User apply(UserResponse userResponse) throws Exception {
                        return new User(userResponse.getLogin(), userResponse.getUserId());
                    }
                });
    }
}
