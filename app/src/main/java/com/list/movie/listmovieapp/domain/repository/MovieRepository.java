package com.list.movie.listmovieapp.domain.repository;

import com.list.movie.listmovieapp.data.db.MovieEntity;

import java.util.List;

import io.reactivex.Single;

public interface MovieRepository {
    Single<List<MovieEntity>> getMovies();
}
