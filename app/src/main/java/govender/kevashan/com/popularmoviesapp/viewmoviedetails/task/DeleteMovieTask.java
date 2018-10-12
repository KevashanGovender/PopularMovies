package govender.kevashan.com.popularmoviesapp.viewmoviedetails.task;

import android.os.AsyncTask;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.IFavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.IFavoriteMovie;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class DeleteMovieTask extends AsyncTask<Void, Void, Void> {

    private IFavoriteMovieRepo repo;
    private IFavoriteMovie view;
    private int id;

    public DeleteMovieTask(IFavoriteMovieRepo repo, IFavoriteMovie view, int id) {
        this.repo = repo;
        this.view = view;
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        repo.deleteMovie(id);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        view.onMovieDelete();
    }
}
