package govender.kevashan.com.popularmoviesapp.viewmovies.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import govender.kevashan.com.popularmoviesapp.R;
import govender.kevashan.com.popularmoviesapp.database.MovieDatabase;
import govender.kevashan.com.popularmoviesapp.serivce.MoviesService;
import govender.kevashan.com.popularmoviesapp.serivce.RetrofitClientInstance;
import govender.kevashan.com.popularmoviesapp.viewmovies.adapter.MoviesAdapter;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.ViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.FavoriteMoviesModel;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.ViewMoviesViewModel;

public class ViewMoviesActivity extends AppCompatActivity implements IViewMoviesView, IFavoriteMovieView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ViewMoviesViewModel viewMoviesViewModel;
    private FavoriteMoviesModel favoriteMoviesModel;
    private MoviesAdapter adapter;
    private SharedPreferences sharedPreferences;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("DISPLAY MOVIES", "ON CREATE");

        setContentView(R.layout.activity_view_movies);
        recyclerView = findViewById(R.id.movies_rv);
        progressBar = findViewById(R.id.progressBar);

        MoviesService moviesService = RetrofitClientInstance.getRetrofitInstance().create(MoviesService.class);
        ViewMoviesRepo repo = new ViewMoviesRepo(moviesService, MovieDatabase.getInstance(getApplicationContext()));

        String key = getString(R.string.the_movieDb_key);
//        String key = "INSERT YOUR OWN KEY";

        viewMoviesViewModel = new ViewMoviesViewModel();
        viewMoviesViewModel.init(repo, key, this);


        favoriteMoviesModel = ViewModelProviders.of(this).get(FavoriteMoviesModel.class);
        favoriteMoviesModel.init(repo, key, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        sharedPreferences = getSharedPreferences("SortOrder", Context.MODE_PRIVATE);

        if(savedInstanceState != null){
            movies = savedInstanceState.getParcelableArrayList("movies");
            showMovies(movies);
        } else {
            switch (sharedPreferences.getString("sort_order", "")) {
                case "popular":
                    viewMoviesViewModel.getPopularMovies();
                    break;
                case "topRated":
                    viewMoviesViewModel.getTopRatedMovies();
                    break;
                case "favorites":
                    favoriteMoviesModel.getFavoriteMovies();
                    break;
                default:
                    Toast.makeText(this, "No sort order found", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pop_movie:
                viewMoviesViewModel.getPopularMovies();
                sharedPreferences.edit().putString("sort_order", "popular").apply();
                return true;
            case R.id.rated_movie:
                viewMoviesViewModel.getTopRatedMovies();
                sharedPreferences.edit().putString("sort_order", "topRated").apply();
                return true;
            case R.id.favorites:
                favoriteMoviesModel.getFavoriteMovies();
                sharedPreferences.edit().putString("sort_order", "favorites").apply();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgressLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        adapter = new MoviesAdapter(movies, this);
        this.movies = movies;

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFavoriteMoviesLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFavoriteMoviesLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showFavoriteMovies() {
        favoriteMoviesModel.getMovies().observe(this, movies -> {
            adapter = new MoviesAdapter(movies, getApplicationContext());

            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) movies);
    }

}
