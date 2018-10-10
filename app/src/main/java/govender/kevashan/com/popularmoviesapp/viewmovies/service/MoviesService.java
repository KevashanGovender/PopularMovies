package govender.kevashan.com.popularmoviesapp.viewmovies.service;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("popular/")
    Call<GetPopularMoviesResponse> getPopularMovies(@Query("api_key") String key);

    @GET("top_rated/")
    Call<GetPopularMoviesResponse> getTopRatedMovies(@Query("api_key") String key);
}
