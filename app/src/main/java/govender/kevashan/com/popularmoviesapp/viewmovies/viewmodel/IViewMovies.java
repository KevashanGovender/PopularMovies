package govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public interface IViewMovies {
    void onStart();
    void onFinish();
    void onSuccess(List<Movie> movies);
    void onError();
}
