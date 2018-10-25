package govender.kevashan.com.popularmoviesapp.viewmovies.repo;

import android.arch.lifecycle.LiveData;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public interface IViewMoviesRepo {

    GetPopularMoviesResponse getPopularMovies(String key);
    GetPopularMoviesResponse getTopRatedMovies(String key);
    LiveData<List<Movie>> getFavoriteMovies();
}