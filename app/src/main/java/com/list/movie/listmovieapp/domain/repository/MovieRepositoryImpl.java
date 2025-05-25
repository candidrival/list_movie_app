package com.list.movie.listmovieapp.domain.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.list.movie.listmovieapp.data.db.MovieDao;
import com.list.movie.listmovieapp.data.db.MovieEntity;
import com.list.movie.listmovieapp.data.retrofit.MovieApiService;
import com.list.movie.listmovieapp.data.retrofit.MovieDto;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class MovieRepositoryImpl implements MovieRepository {
    private final MovieApiService apiService;
    private final MovieDao movieDao;
    private final Context context;

    public MovieRepositoryImpl(MovieApiService apiService, MovieDao movieDao, Context context) {
        this.apiService = apiService;
        this.movieDao = movieDao;
        this.context = context;
    }

    @Override
    public Single<List<MovieEntity>> getMovies() {
        if (isInternetAvailable()) {
            return apiService.getPopularMovies("00ce7103629b2f6502e3faeaedd85b12")
                    .map(response -> {
                        List<MovieEntity> entities = new ArrayList<>();
                        for (MovieDto dto : response.results) {
                            MovieEntity e = new MovieEntity();
                            e.id = dto.id;
                            e.title = dto.title;
                            e.overview = dto.overview;
                            e.posterPath = dto.poster_path;
                            e.voteAverage = dto.vote_average;
                            entities.add(e);
                        }
                        return entities;
                    })
                    .flatMap(entities -> movieDao.insertAll(entities).andThen(Single.just(entities)));
        } else {
            return movieDao.getAll();
        }
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
