package govender.kevashan.com.popularmoviesapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

@Dao
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovie(Movie movie);

    @Query("SELECT * FROM movies_db")
    LiveData<List<Movie>> getFavorites();

    @Query("SELECT * FROM movies_db WHERE id=:id ")
    Movie isMovieAFavorite(int id);

    @Query("DELETE FROM movies_db WHERE id=:id")
    void deleteMovie(int id);
}
