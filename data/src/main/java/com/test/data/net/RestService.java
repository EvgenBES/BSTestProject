package com.test.data.net;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.data.entity.UserResponse;
import com.test.domain.entity.UserRegist;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestService {

    private RestApi restApi;
    private Gson gson;
    private ErrorParserTransformer errorParserTransformer;

    @Inject
    public RestService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttp = new OkHttpClient
                .Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor(
                        new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Log.d("RestService", "HTTP-interceptor: " + message);
                            }
                        })
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                //debugging
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://junior.balinasoft.com/api/account/")
                .client(okHttp)
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer();
    }

    public Observable<UserResponse> registUser(UserRegist userRegist) {
        return restApi
                .registUser(userRegist)
                .compose(errorParserTransformer.<UserResponse, Throwable>parseHttpError());
    }


}
