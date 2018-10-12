package govender.kevashan.com.popularmoviesapp.viewmovies.task;

import android.os.AsyncTask;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.IViewMovies;

public class GetFavoriteMovieTask extends AsyncTask<Void, Void, List<Movie>> {

    private IViewMoviesRepo repo;
    private IViewMovies view;

    public GetFavoriteMovieTask(IViewMoviesRepo repo, IViewMovies view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        return repo.getFavoriteMovies();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        view.onStart();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);

        view.onFinish();

        if (movies != null) {
            view.onSuccess(movies);
        } else {
            view.onError();
        }
    }
}
