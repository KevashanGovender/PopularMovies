package govender.kevashan.com.popularmoviesapp.viewmovies.repo;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import govender.kevashan.com.popularmoviesapp.database.MovieDatabase;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.serivce.MoviesService;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;

public class ViewMoviesRepo implements IViewMoviesRepo {

    private static final String TAG = ViewMoviesRepo.class.getSimpleName();

    private MoviesService service;
    private MovieDatabase database;

    public ViewMoviesRepo(MoviesService service, MovieDatabase database) {
        this.service = service;
        this.database = database;
    }

    @Override
    public GetPopularMoviesResponse getPopularMovies(String key) {
        try {
            return service.getPopularMovies(key).execute().body();
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public GetPopularMoviesResponse getTopRatedMovies(String key) {
        try {
            return service.getTopRatedMovies(key).execute().body();
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
            return null;
        }
    }

    public List<Movie> getFavoriteMovies(){
        return database.daoAccess().getFavorites();
    }
}
