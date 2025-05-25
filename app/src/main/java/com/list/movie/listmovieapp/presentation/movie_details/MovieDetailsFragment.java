package com.list.movie.listmovieapp.presentation.movie_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.list.movie.listmovieapp.R;
import com.list.movie.listmovieapp.data.db.MovieEntity;

public class MovieDetailsFragment extends Fragment implements MovieDetailsView {
    private MovieDetailsPresenter presenter;

    private ImageView poster;
    private TextView title, overview, rating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        poster = view.findViewById(R.id.poster);
        title = view.findViewById(R.id.title);
        overview = view.findViewById(R.id.overview);
        rating = view.findViewById(R.id.rating);

        MovieEntity movie = (MovieEntity) getArguments().getSerializable("movie");
        presenter = new MovieDetailsPresenter(this, movie);
        presenter.loadMovieDetails();

        return view;
    }

    @Override
    public void showMovieDetails(MovieEntity movie) {
        title.setText(movie.title);
        overview.setText(movie.overview);
        rating.setText(String.valueOf(movie.voteAverage));
        Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w185" + movie.posterPath)
                .into(poster);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
