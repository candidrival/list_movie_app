package com.list.movie.listmovieapp.presentation.movie_list;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.list.movie.listmovieapp.R;
import com.list.movie.listmovieapp.data.db.AppDatabase;
import com.list.movie.listmovieapp.data.db.MovieDao;
import com.list.movie.listmovieapp.data.db.MovieEntity;
import com.list.movie.listmovieapp.data.retrofit.MovieApiService;
import com.list.movie.listmovieapp.di.RetrofitClient;
import com.list.movie.listmovieapp.domain.repository.MovieRepository;
import com.list.movie.listmovieapp.domain.repository.MovieRepositoryImpl;
import com.list.movie.listmovieapp.domain.usecase.GetPopularMoviesUseCase;
import com.list.movie.listmovieapp.domain.usecase.GetPopularMoviesUseCaseImpl;
import com.list.movie.listmovieapp.presentation.movie_details.MovieDetailsFragment;

import java.util.List;

public class MovieListFragment extends Fragment implements MovieListView {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieListPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(movie -> openDetails(movie));
        recyclerView.setAdapter(adapter);

        MovieApiService api = RetrofitClient.getInstance().create(MovieApiService.class);
        MovieDao dao = Room.databaseBuilder(getContext(), AppDatabase.class, "db").build().movieDao();
        MovieRepository repo = new MovieRepositoryImpl(api, dao, getContext());
        GetPopularMoviesUseCase useCase = new GetPopularMoviesUseCaseImpl(repo);
        presenter = new MovieListPresenter(useCase, this);

        presenter.loadMovies();

        return view;
    }

    private void openDetails(MovieEntity movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.main, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showMovies(List<MovieEntity> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
