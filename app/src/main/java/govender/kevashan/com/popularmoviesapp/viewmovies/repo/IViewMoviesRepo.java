package govender.kevashan.com.popularmoviesapp.viewmovies.repo;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;

public interface IViewMoviesRepo {

    GetPopularMoviesResponse getPopularMovies(String key);
    GetPopularMoviesResponse getTopRatedMovies(String key);
}
