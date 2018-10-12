package govender.kevashan.com.popularmoviesapp.viewmoviedetails.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import govender.kevashan.com.popularmoviesapp.R;
import govender.kevashan.com.popularmoviesapp.database.MovieDatabase;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.FavoriteMovieRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.FavoriteMovieViewModel;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity implements IMovieDetailsView {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w185//";

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

        Intent recievedIntent = getIntent();
        Movie recievedMovie = recievedIntent.getParcelableExtra("movie");

        Picasso.with(this).load(BASE_IMAGE_URL + recievedMovie.getPosterPath()).into(moviePoster);

        movieTitle.setText(recievedMovie.getTitle());
        movieRelaseDate.setText(recievedMovie.getReleaseDate());
        movieRating.setText(String.valueOf(recievedMovie.getVoteAverage()));
        movieOverview.setText(recievedMovie.getOverview());

        MovieDatabase database = MovieDatabase.getInstance(getApplicationContext());

        FavoriteMovieRepo repo = new FavoriteMovieRepo(database);

        FavoriteMovieViewModel viewModel = new FavoriteMovieViewModel(repo, this);

        favBtn.setOnClickListener(view -> viewModel.checkMovie(recievedMovie));
    }

    @Override
    public void showMovieFavorited() {
        Toast.makeText(this, "Movie added to favorites", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovieRemoved() {
        Toast.makeText(this, "Movie has been removed from favorites", Toast.LENGTH_LONG).show();
    }
}
