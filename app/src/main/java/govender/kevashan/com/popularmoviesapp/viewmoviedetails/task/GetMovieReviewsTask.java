package govender.kevashan.com.popularmoviesapp.viewmoviedetails.task;

import android.os.AsyncTask;

import govender.kevashan.com.popularmoviesapp.viewmoviedetails.models.GetReviewsResponse;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.repo.ITrailerRepo;
import govender.kevashan.com.popularmoviesapp.viewmoviedetails.viewmodel.ITrailer;

public class GetMovieReviewsTask  extends AsyncTask<Void, Void, GetReviewsResponse> {

    private ITrailerRepo repo;
    private int movieId;
    private ITrailer view;

    public GetMovieReviewsTask(ITrailerRepo repo, int movieId, ITrailer view) {
        this.repo = repo;
        this.movieId = movieId;
        this.view = view;
    }

    @Override
    protected GetReviewsResponse doInBackground(Void... voids) {
        return repo.getReview(movieId);
    }

    @Override
    protected void onPostExecute(GetReviewsResponse getReviewsResponse) {
        super.onPostExecute(getReviewsResponse);
        if(getReviewsResponse != null){
            view.showReviews(getReviewsResponse);
        }
    }
}
