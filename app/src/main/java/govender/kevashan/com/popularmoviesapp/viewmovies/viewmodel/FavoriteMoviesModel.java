package govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.IFavoriteMovie;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.task.ViewMoviesTaskFactory;
import govender.kevashan.com.popularmoviesapp.viewmovies.view.IFavoriteMovieView;

public class FavoriteMoviesModel extends AndroidViewModel implements IFavoriteMovies {

    private ViewMoviesTaskFactory taskFactory;
    private IFavoriteMovieView view;
    private LiveData<List<Movie>> movies;

    public FavoriteMoviesModel(@NonNull Application application) {
        super(application);
    }

    public void init(IViewMoviesRepo repo, String key, IFavoriteMovieView view) {
        this.view = view;

        taskFactory = new ViewMoviesTaskFactory(repo, key);
    }

    public void getFavoriteMovies(){
        taskFactory.getFavoriteMovieTask(this).execute();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onSuccess(LiveData<List<Movie>> movies) {
        this.movies = movies;
        view.showFavoriteMovies();
    }

    @Override
    public void onError() {

    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
