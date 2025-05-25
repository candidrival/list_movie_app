package com.list.movie.listmovieapp.presentation.movie_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.list.movie.listmovieapp.R;
import com.list.movie.listmovieapp.data.db.MovieEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieEntity> movies = new ArrayList<>();
    private final Consumer<MovieEntity> onClick;

    public MovieAdapter(Consumer<MovieEntity> onClick) {
        this.onClick = onClick;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, overview, rating;
        private final ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            overview = itemView.findViewById(R.id.overview);
            rating = itemView.findViewById(R.id.rating);
            poster = itemView.findViewById(R.id.poster);
        }

        public void bind(MovieEntity movie) {
            title.setText(movie.title);
            overview.setText(movie.overview);
            rating.setText(String.valueOf(movie.voteAverage));
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + movie.posterPath)
                    .into(poster);
            itemView.setOnClickListener(v -> onClick.accept(movie));
        }
    }
}
