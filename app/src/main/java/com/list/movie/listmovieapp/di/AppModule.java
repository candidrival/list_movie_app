package com.list.movie.listmovieapp.di;

import android.content.Context;

import androidx.room.Room;

import com.list.movie.listmovieapp.data.db.AppDatabase;
import com.list.movie.listmovieapp.data.db.MovieDao;
import com.list.movie.listmovieapp.data.retrofit.MovieApiService;
import com.list.movie.listmovieapp.domain.repository.MovieRepository;
import com.list.movie.listmovieapp.domain.repository.MovieRepositoryImpl;
import com.list.movie.listmovieapp.domain.usecase.GetPopularMoviesUseCase;
import com.list.movie.listmovieapp.domain.usecase.GetPopularMoviesUseCaseImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    MovieDao provideMovieDao() {
        return Room.databaseBuilder(context, AppDatabase.class, "db").build().movieDao();
    }

    @Provides
    MovieApiService provideMovieApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovieApiService.class);
    }

    @Provides
    MovieRepository provideRepository(MovieApiService api, MovieDao dao) {
        return new MovieRepositoryImpl(api, dao, context);
    }

    @Provides
    GetPopularMoviesUseCase provideUseCase(MovieRepository repository) {
        return new GetPopularMoviesUseCaseImpl(repository);
    }
}

