package govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.task.ViewMoviesTaskFactory;
import govender.kevashan.com.popularmoviesapp.viewmovies.view.IViewMoviesView;

public class ViewMoviesViewModel implements IViewMovies {

    private ViewMoviesTaskFactory taskFactory;
    private IViewMoviesView view;

    public ViewMoviesViewModel(IViewMoviesRepo repo, String key, IViewMoviesView view) {
        this.view = view;

        taskFactory = new ViewMoviesTaskFactory(repo, key, this);
    }

    public void getPopularMovies(){
        taskFactory.getPopularMoviesTask().execute();
    }

    public void getTopRatedMovies(){
        taskFactory.getTopRatedMovieTask().execute();
    }

    public void getFavoriteMovies(){
        taskFactory.getFavoriteMovieTask().execute();
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
