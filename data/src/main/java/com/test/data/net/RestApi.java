package com.test.data.net;

import com.test.data.entity.UserResponse;
import com.test.domain.entity.UserRegist;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @POST("signup")
    Observable<UserResponse> registUser(@Body UserRegist user);

}
