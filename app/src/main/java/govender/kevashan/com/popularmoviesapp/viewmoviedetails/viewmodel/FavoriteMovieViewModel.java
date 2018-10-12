package govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.IFavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.task.FavoriteMovieTaskFactory;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.view.IMovieDetailsView;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class FavoriteMovieViewModel implements IFavoriteMovie {

    private static final String TAG = FavoriteMovieViewModel.class.getSimpleName();

    private IFavoriteMovieRepo repo;
    private FavoriteMovieTaskFactory taskFactory;
    private IMovieDetailsView view;

    public FavoriteMovieViewModel(IFavoriteMovieRepo repo, IMovieDetailsView view) {
        this.repo = repo;
        this.view = view;

        taskFactory = new FavoriteMovieTaskFactory();
    }

    private void favoriteMovie(Movie movie){
        taskFactory.getFavoriteMovieTask(repo, movie, this).execute();
    }

    private void deleteMovie(Movie movie){
        taskFactory.deleteMovieTask(repo, this, movie.getId()).execute();
    }

    public void checkMovie(Movie movie){
        Movie movieToCheck = null;
        try {
            movieToCheck = taskFactory.checkMovieTask(repo, movie.getId()).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }
        if(movieToCheck != null){
            deleteMovie(movie);
        } else {
            favoriteMovie(movie);
        }
    }

    @Override
    public void onMovieFavorite() {
        view.showMovieFavorited();
    }

    @Override
    public void onMovieDelete() {
        view.showMovieRemoved();
    }
}
