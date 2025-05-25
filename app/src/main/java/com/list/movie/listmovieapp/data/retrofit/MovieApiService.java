package com.list.movie.listmovieapp.data.retrofit;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Single<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
}