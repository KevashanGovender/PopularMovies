package govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo;

import android.util.Log;

import java.io.IOException;

import govender.kevashan.com.popularmoviesapp.serivce.MoviesService;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetTrailerResponse;

public class TrailerRepo implements ITrailerRepo {

    private static final String TAG = TrailerRepo.class.getSimpleName();

    private String key;
    private MoviesService service;

    public TrailerRepo(String key, MoviesService service) {
        this.key = key;
        this.service = service;
    }

    @Override
    public GetTrailerResponse getTrailer(int movieId) {
        try {
            return service.getMovieTrailer(movieId, key).execute().body();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public GetReviewsResponse getReview(int movieId) {
        try {
            return service.getMovieReviews(movieId, key).execute().body();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
