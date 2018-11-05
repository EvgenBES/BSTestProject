package com.test.domain.repositories;

import com.test.domain.entity.User;
import com.test.domain.entity.UserRegist;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<User> registUser (UserRegist user);
}
