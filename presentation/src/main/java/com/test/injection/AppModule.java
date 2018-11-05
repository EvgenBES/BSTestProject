package com.test.injection;

import android.content.Context;

import com.test.data.repositories.UserRepositoryImpl;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.UserRepository;
import com.test.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    public static UserRepository provideUserRepository(UserRepositoryImpl userRepository) {
        return userRepository;
    }

    @Singleton
    @Provides
    public static PostExecutionThread provideUIThread(UIThread uiThread) {
        return uiThread;
    }
}
