package govender.kevashan.com.popularmoviesapp.viewmoviedetails.task;

import android.os.AsyncTask;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.IFavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.IFavoriteMovie;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class FavoriteMovieTask extends AsyncTask<Void, Void, Void> {

    private IFavoriteMovieRepo repo;
    private Movie movie;
    private IFavoriteMovie view;


    public FavoriteMovieTask(IFavoriteMovieRepo repo, Movie movie, IFavoriteMovie view) {
        this.repo = repo;
        this.movie = movie;
        this.view = view;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        repo.favoriteMovie(movie);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        view.onMovieFavorite();
    }
}
