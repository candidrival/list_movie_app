package com.list.movie.listmovieapp.presentation.movie_details;

import com.list.movie.listmovieapp.data.db.MovieEntity;

public class MovieDetailsPresenter {
    private final MovieDetailsView view;
    private final MovieEntity movie;

    public MovieDetailsPresenter(MovieDetailsView view, MovieEntity movie) {
        this.view = view;
        this.movie = movie;
    }

    public void loadMovieDetails() {
        view.showMovieDetails(movie);
    }
}
