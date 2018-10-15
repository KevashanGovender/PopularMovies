package govender.kevashan.com.popularmoviesapp.serivce;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetTrailerResponse;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("popular/")
    Call<GetPopularMoviesResponse> getPopularMovies(@Query("api_key") String key);

    @GET("top_rated/")
    Call<GetPopularMoviesResponse> getTopRatedMovies(@Query("api_key") String key);

    @GET("{movie_id}/videos")
    Call<GetTrailerResponse> getMovieTrailer(@Path("movie_id") int movieId, @Query("api_key") String key);

    @GET("{movie_id}/reviews")
    Call<GetReviewsResponse> getMovieReviews(@Path("movie_id") int id, @Query("api_key") String key);
}
