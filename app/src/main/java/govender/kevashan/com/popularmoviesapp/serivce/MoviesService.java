package govender.kevashan.com.popularmoviesapp.serivce;

import govender.kevashan.com.popularmoviesapp.viewmovies.model.GetPopularMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("popular/")
    Call<GetPopularMoviesResponse> getPopularMovies(@Query("api_key") String key);
}
