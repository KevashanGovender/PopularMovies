package govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public interface IFavoriteMovieRepo {

    void favoriteMovie(Movie movie);
    Movie isMovieFavorited(int id);
    void deleteMovie(int id);
}
