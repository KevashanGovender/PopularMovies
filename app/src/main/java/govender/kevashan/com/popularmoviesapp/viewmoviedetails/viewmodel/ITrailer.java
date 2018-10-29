package govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetTrailerResponse;

public interface ITrailer {

    void showTrailer(GetTrailerResponse getTrailerResponse);
    void showReviews(GetReviewsResponse getReviewsResponse);
}
