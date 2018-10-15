package govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.IFavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.ITrailerRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.task.MovieDetailsTaskFactory;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.view.IMovieDetailsView;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class MovieDetailsViewModel implements IFavoriteMovie, ITrailer {

    private static final String TAG = MovieDetailsViewModel.class.getSimpleName();

    private IFavoriteMovieRepo repo;
    private MovieDetailsTaskFactory taskFactory;
    private IMovieDetailsView view;
    private ITrailerRepo trailerRepo;

    public MovieDetailsViewModel(IFavoriteMovieRepo repo, IMovieDetailsView view, ITrailerRepo trailerRepo) {
        this.repo = repo;
        this.view = view;
        this.trailerRepo = trailerRepo;

        taskFactory = new MovieDetailsTaskFactory();
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

    public void getMovieTrailer(int id){
        taskFactory.getTrailerTask(trailerRepo, this, id).execute();
    }

    public void getMovieReviews(int id){
        taskFactory.getMovieReviewsTask(trailerRepo, this, id).execute();
    }

    @Override
    public void onMovieFavorite() {
        view.showMovieFavorited();
    }

    @Override
    public void onMovieDelete() {
        view.showMovieRemoved();
    }

    @Override
    public void showTrailer(String key) {
        view.showMovieTrailer(key);
    }

    @Override
    public void showReviews(GetReviewsResponse getReviewsResponse) {
        view.showReviews(getReviewsResponse.getResults());
    }
}
