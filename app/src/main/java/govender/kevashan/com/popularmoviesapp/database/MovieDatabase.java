package govender.kevashan.com.popularmoviesapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

   private static MovieDatabase instance;

   public abstract DaoAccess daoAccess();

   public static MovieDatabase getInstance(Context context){
      if(instance == null){
         instance = Room.databaseBuilder(context, MovieDatabase.class,
                 "movie_db").fallbackToDestructiveMigration().build();
      }
      return instance;
   }
}
