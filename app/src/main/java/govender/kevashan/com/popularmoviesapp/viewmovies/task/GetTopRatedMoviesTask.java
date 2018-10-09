package govender.kevashan.com.popularmoviesapp.viewmovies.task;

import android.os.AsyncTask;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.IViewMovies;

public class GetTopRatedMoviesTask extends AsyncTask<Void, String, GetPopularMoviesResponse> {

    private IViewMoviesRepo repo;
    private String key;
    private IViewMovies view;

    public GetTopRatedMoviesTask(IViewMoviesRepo repo, String key, IViewMovies view) {
        this.repo = repo;
        this.key = key;
        this.view = view;
    }

    @Override
    protected GetPopularMoviesResponse doInBackground(Void... voids) {
        return repo.getTopRatedMovies(key);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        view.onStart();
    }

    @Override
    protected void onPostExecute(GetPopularMoviesResponse getPopularMoviesResponse) {
        super.onPostExecute(getPopularMoviesResponse);

        view.onFinish();

        if (getPopularMoviesResponse != null) {
            view.onSuccess(getPopularMoviesResponse.getMovies());
        } else {
            view.onError();
        }
    }
}
