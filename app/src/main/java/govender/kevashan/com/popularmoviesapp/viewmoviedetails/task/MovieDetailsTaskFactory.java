package govender.kevashan.com.popularmoviesapp.viewmoviedetails.task;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.IFavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.ITrailerRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.IFavoriteMovie;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.ITrailer;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class MovieDetailsTaskFactory {

    public FavoriteMovieTask getFavoriteMovieTask(IFavoriteMovieRepo repo, Movie movie, IFavoriteMovie view){
        return new FavoriteMovieTask(repo, movie, view);
    }

    public CheckMovieTask checkMovieTask(IFavoriteMovieRepo repo, int id){
        return new CheckMovieTask(repo, id);
    }

    public DeleteMovieTask deleteMovieTask(IFavoriteMovieRepo repo, IFavoriteMovie view, int id){
        return new DeleteMovieTask(repo, view, id);
    }

    public GetTrailerTask getTrailerTask(ITrailerRepo repo, ITrailer view, int id){
        return new GetTrailerTask(repo, id, view);
    }

    public GetMovieReviewsTask getMovieReviewsTask(ITrailerRepo repo, ITrailer view, int id){
        return new GetMovieReviewsTask(repo, id, view);
    }

}
