package govender.kevashan.com.popularmoviesapp.viewmovies.view;

import android.arch.lifecycle.LiveData;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public interface IFavoriteMovieView {

    void showFavoriteMoviesLoading();
    void hideFavoriteMoviesLoading();
    void showFavoriteMovies();
}
