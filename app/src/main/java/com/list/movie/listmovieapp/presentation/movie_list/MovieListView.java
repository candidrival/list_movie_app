package com.list.movie.listmovieapp.presentation.movie_list;

import com.list.movie.listmovieapp.data.db.MovieEntity;

import java.util.List;

public interface MovieListView {
    void showMovies(List<MovieEntity> movies);
    void showError(String message);
}
