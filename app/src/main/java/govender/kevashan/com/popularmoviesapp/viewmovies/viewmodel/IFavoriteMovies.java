package govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel;

import android.arch.lifecycle.LiveData;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public interface IFavoriteMovies {

    void onStart();
    void onFinish();
    void onSuccess(LiveData<List<Movie>> movies);
    void onError();
}
