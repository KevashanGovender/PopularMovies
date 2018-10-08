package govender.kevashan.com.popularmoviesapp.viewmovies.view;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public interface IViewMoviesView {
    void showProgressLoader();
    void hideProgressLoader();
    void showMovies(List<Movie> movies);
    void showError();
}
