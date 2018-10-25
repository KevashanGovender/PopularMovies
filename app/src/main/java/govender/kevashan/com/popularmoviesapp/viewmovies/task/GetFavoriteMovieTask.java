package govender.kevashan.com.popularmoviesapp.viewmovies.task;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.IFavoriteMovies;

public class GetFavoriteMovieTask extends AsyncTask<Void, Void, LiveData<List<Movie>>> {

    private IViewMoviesRepo repo;
    private IFavoriteMovies view;

    public GetFavoriteMovieTask(IViewMoviesRepo repo, IFavoriteMovies view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    protected  LiveData<List<Movie>> doInBackground(Void... voids) {
        return repo.getFavoriteMovies();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        view.onStart();
    }

    @Override
    protected void onPostExecute(LiveData<List<Movie>>movies) {
        super.onPostExecute(movies);

        view.onFinish();

        if (movies != null) {
            view.onSuccess(movies);
        } else {
            view.onError();
        }
    }
}
