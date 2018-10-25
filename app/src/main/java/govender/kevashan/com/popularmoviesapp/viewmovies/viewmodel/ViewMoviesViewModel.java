package govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.task.ViewMoviesTaskFactory;
import govender.kevashan.com.popularmoviesapp.viewmovies.view.IViewMoviesView;

public class ViewMoviesViewModel implements IViewMovies{

    private ViewMoviesTaskFactory taskFactory;
    private IViewMoviesView view;

    public void init(IViewMoviesRepo repo, String key, IViewMoviesView view) {
        this.view = view;

        taskFactory = new ViewMoviesTaskFactory(repo, key);
    }

    public void getPopularMovies(){
        taskFactory.getPopularMoviesTask(this).execute();
    }

    public void getTopRatedMovies(){
        taskFactory.getTopRatedMovieTask(this).execute();
    }

    @Override
    public void onStart() {
        view.showProgressLoader();
    }

    @Override
    public void onFinish() {
        view.hideProgressLoader();
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        view.showMovies(movies);
    }

    @Override
    public void onError() {
        view.showError();
    }
}
