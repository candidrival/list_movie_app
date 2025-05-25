package com.list.movie.listmovieapp.domain.usecase;

import com.list.movie.listmovieapp.data.db.MovieEntity;
import com.list.movie.listmovieapp.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.Single;

public class GetPopularMoviesUseCaseImpl implements GetPopularMoviesUseCase {
    private final MovieRepository repository;

    public GetPopularMoviesUseCaseImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<MovieEntity>> execute() {
        return repository.getMovies();
    }
}
