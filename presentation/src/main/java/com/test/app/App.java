package com.test.app;

import android.app.Application;
import android.test.com.testproject.R;

import com.facebook.stetho.Stetho;
import com.test.injection.AppComponent;
import com.test.injection.AppModule;
import com.test.injection.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
