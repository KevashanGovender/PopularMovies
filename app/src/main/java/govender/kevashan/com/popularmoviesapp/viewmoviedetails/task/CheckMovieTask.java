package govender.kevashan.com.popularmoviesapp.viewmoviedetails.task;

import android.os.AsyncTask;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.IFavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class CheckMovieTask extends AsyncTask<Void, Void, Movie> {

    private IFavoriteMovieRepo repo;
    private int id;

    public CheckMovieTask(IFavoriteMovieRepo repo, int id) {
        this.repo = repo;
        this.id = id;
    }

    @Override
    protected Movie doInBackground(Void... voids) {
        return repo.isMovieFavorited(id);
    }
}
