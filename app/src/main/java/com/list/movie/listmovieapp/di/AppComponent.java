package com.list.movie.listmovieapp.di;

import com.list.movie.listmovieapp.App;
import com.list.movie.listmovieapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    void inject(MainActivity activity);
}
