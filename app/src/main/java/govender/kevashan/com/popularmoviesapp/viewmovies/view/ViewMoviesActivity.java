package govender.kevashan.com.popularmoviesapp.viewmovies.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.R;
import govender.kevashan.com.popularmoviesapp.viewmovies.service.MoviesService;
import govender.kevashan.com.popularmoviesapp.serivce.RetrofitClientInstance;
import govender.kevashan.com.popularmoviesapp.viewmovies.adapter.MoviesAdapter;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.ViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.ViewMoviesViewModel;

public class ViewMoviesActivity extends AppCompatActivity implements IViewMoviesView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ViewMoviesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movies);
        recyclerView = findViewById(R.id.movies_rv);
        progressBar = findViewById(R.id.progressBar);

        MoviesService moviesService = RetrofitClientInstance.getRetrofitInstance().create(MoviesService.class);
        ViewMoviesRepo repo = new ViewMoviesRepo(moviesService);

        String key = getString(R.string.the_movieDb_key);
//        String key = "INSERT YOUR OWN KEY";

        viewModel = new ViewMoviesViewModel(repo, key, this);

        viewModel.getPopularMovies();
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
                viewModel.getPopularMovies();
                return true;
            case R.id.rated_movie:
                viewModel.getTopRatedMovies();
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
        MoviesAdapter adapter = new MoviesAdapter(movies, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
}
