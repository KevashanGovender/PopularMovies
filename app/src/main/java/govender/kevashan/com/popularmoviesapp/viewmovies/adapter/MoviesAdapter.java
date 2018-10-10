package govender.kevashan.com.popularmoviesapp.viewmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.R;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.MovieDetailsActivity;
import govender.kevashan.com.popularmoviesapp.viewmovies.model.Movie;
import govender.kevashan.com.popularmoviesapp.viewmovies.view.ViewMoviesActivity;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w185//";

    private List<Movie> movieList;
    private Context context;

    public MoviesAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_display_row,
                parent, false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
        int posterIndexL;
        int posterIndexR;

        posterIndexL = 2 * position;
        posterIndexR = posterIndexL + 1;

        if(posterIndexL < movieList.size()) {
            Movie poster1 = movieList.get(posterIndexL);
            Picasso.with(context).load(BASE_IMAGE_URL + poster1.getPosterPath()).into(holder.poster1);

            holder.poster1.setOnClickListener(v -> {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie", poster1);
                context.startActivity(intent);
            });
        }

        if(posterIndexR < movieList.size()) {
            Movie poster2 = movieList.get(posterIndexR);
            Picasso.with(context).load(BASE_IMAGE_URL + poster2.getPosterPath()).into(holder.poster2);

            holder.poster2.setOnClickListener(v -> {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie", poster2);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        int i = movieList.size()/2;
        int s = movieList.size()%2;

        return i + s;
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder{

        ImageView poster1, poster2;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            poster1 = itemView.findViewById(R.id.poster_1);
            poster2 = itemView.findViewById(R.id.poster_2);
        }
    }
}
