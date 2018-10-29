package govender.kevashan.com.popularmoviesapp.viewmoviedetails.task;

import android.os.AsyncTask;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetTrailerResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.ITrailerRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.ITrailer;

public class GetTrailerTask extends AsyncTask<Void, Void, GetTrailerResponse> {

    private ITrailerRepo repo;
    private int movieId;
    private ITrailer view;

    public GetTrailerTask(ITrailerRepo repo, int movieId, ITrailer view) {
        this.repo = repo;
        this.movieId = movieId;
        this.view = view;
    }

    @Override
    protected GetTrailerResponse doInBackground(Void... voids) {
        return repo.getTrailer(movieId);
    }

    @Override
    protected void onPostExecute(GetTrailerResponse getTrailerResponse) {
        super.onPostExecute(getTrailerResponse);
        if(getTrailerResponse != null){
            view.showTrailer(getTrailerResponse);
        }
    }
}
