package govender.kevashan.com.popularmoviesapp.viewmovies.task;

import govender.kevashan.com.popularmoviesapp.viewmovies.repo.IViewMoviesRepo;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.IFavoriteMovies;
import govender.kevashan.com.popularmoviesapp.viewmovies.viewmodel.IViewMovies;

public class ViewMoviesTaskFactory {

    private IViewMoviesRepo repo;
    private String key;

    public ViewMoviesTaskFactory(IViewMoviesRepo repo, String key) {
        this.repo = repo;
        this.key = key;
    }

    public GetPopularMoviesTask getPopularMoviesTask(IViewMovies view){
       return new GetPopularMoviesTask(repo, key, view);
    }

    public GetTopRatedMoviesTask getTopRatedMovieTask(IViewMovies view){
        return new GetTopRatedMoviesTask(repo, key, view);
    }

    public GetFavoriteMovieTask getFavoriteMovieTask(IFavoriteMovies favoriteMoviesView){
        return new GetFavoriteMovieTask(repo, favoriteMoviesView);
    }
}
