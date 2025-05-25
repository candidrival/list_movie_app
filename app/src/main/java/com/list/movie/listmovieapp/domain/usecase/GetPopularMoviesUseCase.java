package com.list.movie.listmovieapp.domain.usecase;

import com.list.movie.listmovieapp.data.db.MovieEntity;

import java.util.List;

import io.reactivex.Single;

public interface GetPopularMoviesUseCase {
    Single<List<MovieEntity>> execute();
}
