package govender.kevashan.com.popularmoviesapp.viewmoviedetails.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import govender.kevashan.com.popularmoviesapp.R;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.Trailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private List<Trailer> movieTrailers;
    private Context context;

    public TrailerAdapter(List<Trailer> movieTrailers, Context context) {
        this.movieTrailers = movieTrailers;
        this.context = context;
    }


    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_display_row,
                viewGroup, false);
        return new TrailerAdapter.TrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, int i) {
        trailerViewHolder.trailerBtn.setText(movieTrailers.get(i).getName());
        trailerViewHolder.trailerBtn.setOnClickListener(view -> {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + movieTrailers.get(i).getKey()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + movieTrailers.get(i).getKey()));
            try {
                context.startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                context.startActivity(webIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieTrailers.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder{

        Button trailerBtn;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            trailerBtn = itemView.findViewById(R.id.trailer_btn)
;        }
    }
}
