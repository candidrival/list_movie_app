package com.list.movie.listmovieapp.presentation.movie_details;

import com.list.movie.listmovieapp.data.db.MovieEntity;

public interface MovieDetailsView {
    void showMovieDetails(MovieEntity movie);
    void showError(String message);
}
