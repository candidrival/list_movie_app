package com.list.movie.listmovieapp.presentation.movie_list;

import com.list.movie.listmovieapp.domain.usecase.GetPopularMoviesUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieListPresenter {
    private final GetPopularMoviesUseCase useCase;
    private final MovieListView view;

    public MovieListPresenter(GetPopularMoviesUseCase useCase, MovieListView view) {
        this.useCase = useCase;
        this.view = view;
    }

    public void loadMovies() {
        useCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showMovies, throwable -> view.showError(throwable.getMessage()));
    }
}
