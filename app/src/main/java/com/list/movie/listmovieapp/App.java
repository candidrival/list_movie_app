package com.list.movie.listmovieapp;

import android.app.Application;

import com.list.movie.listmovieapp.di.AppComponent;
import com.list.movie.listmovieapp.di.AppModule;
import com.list.movie.listmovieapp.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}