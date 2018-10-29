package govender.kevashan.com.popularmoviesapp.viewmoviedetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.R;
import govender.kevashan.com.popularmoviesapp.database.MovieDatabase;
import govender.kevashan.com.popularmoviesapp.serivce.MoviesService;
import govender.kevashan.com.popularmoviesapp.serivce.RetrofitClientInstance;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.adapter.ReviewAdapter;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.adapter.TrailerAdapter;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.Review;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.Trailer;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.FavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.TrailerRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.MovieDetailsViewModel;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity implements IMovieDetailsView {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w185//";

    private RecyclerView reviewRV;
    private RecyclerView trailerRV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ImageView moviePoster = findViewById(R.id.poster_iv);
        TextView movieTitle = findViewById(R.id.movie_title_tv);
        TextView movieRelaseDate = findViewById(R.id.release_date_tv);
        TextView movieRating = findViewById(R.id.rating_tv);
        TextView movieOverview = findViewById(R.id.overview_tv);
        ImageView favBtn = findViewById(R.id.fav_movie_btn);
        reviewRV = findViewById(R.id.review_rv);
        trailerRV = findViewById(R.id.trailer_rv);

        Intent recievedIntent = getIntent();
        Movie recievedMovie = recievedIntent.getParcelableExtra("movie");

        Picasso.with(this).load(BASE_IMAGE_URL + recievedMovie.getPosterPath()).into(moviePoster);

        movieTitle.setText(recievedMovie.getTitle());
        movieRelaseDate.setText(recievedMovie.getReleaseDate());
        movieRating.setText(String.valueOf(recievedMovie.getVoteAverage()));
        movieOverview.setText(recievedMovie.getOverview());

        MovieDatabase database = MovieDatabase.getInstance(getApplicationContext());

        FavoriteMovieRepo repo = new FavoriteMovieRepo(database);

        MoviesService service = RetrofitClientInstance.getRetrofitInstance().create(MoviesService.class);
        String key = getString(R.string.the_movieDb_key);
//        String key = INSERT YOUR OWN KEY
        TrailerRepo trailerRepo = new TrailerRepo(key, service);

        MovieDetailsViewModel viewModel = new MovieDetailsViewModel(repo, this, trailerRepo);

        favBtn.setOnClickListener(view -> viewModel.checkMovie(recievedMovie));
        viewModel.getMovieTrailer(recievedMovie.getId());
        viewModel.getMovieReviews(recievedMovie.getId());
    }

    @Override
    public void showMovieFavorited() {
        Toast.makeText(this, "Movie added to favorites", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovieRemoved() {
        Toast.makeText(this, "Movie has been removed from favorites", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovieTrailer(List<Trailer> trailers) {
//        trailerBtn.setOnClickListener(view -> {
//            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
//            Intent webIntent = new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("http://www.youtube.com/watch?v=" + key));
//            try {
//                startActivity(appIntent);
//            } catch (ActivityNotFoundException ex) {
//                startActivity(webIntent);
//            }
//        });

        TrailerAdapter trailerAdapter = new TrailerAdapter(trailers, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        trailerRV.setLayoutManager(layoutManager);
        trailerRV.setAdapter(trailerAdapter);
    }

    @Override
    public void showReviews(List<Review> reviews) {
        ReviewAdapter adapter = new ReviewAdapter(reviews);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        reviewRV.setLayoutManager(layoutManager);
        reviewRV.setAdapter(adapter);
    }
}
