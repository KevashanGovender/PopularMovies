package govender.kevashan.com.popularmoviesapp.viewmovies.repo;

import android.util.Log;

import java.io.IOException;

import govender.kevashan.com.popularmoviesapp.serivce.MoviesService;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;

public class ViewMoviesRepo implements IViewMoviesRepo {

    private static final String TAG = ViewMoviesRepo.class.getSimpleName();

    private MoviesService service;

    public ViewMoviesRepo(MoviesService service) {
        this.service = service;
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
}
