package govender.kevashan.com.popularmoviesapp.viewmoviedetails.view;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.Review;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.Trailer;

public interface IMovieDetailsView {

    void showMovieFavorited();
    void showMovieRemoved();
    void showMovieTrailer(List<Trailer> trailers);
    void showReviews(List<Review> reviews);
}
