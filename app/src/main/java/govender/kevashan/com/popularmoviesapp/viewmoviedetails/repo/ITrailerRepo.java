package govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetTrailerResponse;

public interface ITrailerRepo {

    GetTrailerResponse getTrailer(int movieId);
    GetReviewsResponse getReview(int movieId);
}
