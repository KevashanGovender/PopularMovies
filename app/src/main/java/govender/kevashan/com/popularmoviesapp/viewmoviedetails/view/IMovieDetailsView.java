package govender.kevashan.com.popularmoviesapp.viewmoviedetails.view;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.Review;

public interface IMovieDetailsView {

    void showMovieFavorited();
    void showMovieRemoved();
    void showMovieTrailer(String key);
    void showReviews(List<Review> reviews);
}
