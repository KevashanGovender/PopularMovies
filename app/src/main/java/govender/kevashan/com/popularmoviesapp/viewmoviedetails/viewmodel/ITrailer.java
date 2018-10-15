package govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;

public interface ITrailer {

    void showTrailer(String key);
    void showReviews(GetReviewsResponse getReviewsResponse);
}
