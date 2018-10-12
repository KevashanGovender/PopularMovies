package govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo;

import govender.kevashan.com.popularmoviesapp.database.MovieDatabase;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class FavoriteMovieRepo implements IFavoriteMovieRepo {

    private MovieDatabase database;

    public FavoriteMovieRepo(MovieDatabase database) {
        this.database = database;
    }

    @Override
    public void favoriteMovie(Movie movie) {
        database.daoAccess().insertMovie(movie);
    }

    @Override
    public Movie isMovieFavorited(int id) {
       return database.daoAccess().isMovieAFavorite(id);
    }

    @Override
    public void deleteMovie(int id) {
        database.daoAccess().deleteMovie(id);
    }
}
