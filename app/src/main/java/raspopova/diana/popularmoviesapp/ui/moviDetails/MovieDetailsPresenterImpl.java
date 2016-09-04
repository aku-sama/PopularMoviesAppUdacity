package raspopova.diana.popularmoviesapp.ui.moviDetails;

import android.content.res.Resources;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.Config;
import raspopova.diana.popularmoviesapp.app.MovieApplication;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;

/**
 * Created by Diana on 9/3/2016.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    private MovieDetailsView view;
    private movieObject movie;

    public MovieDetailsPresenterImpl(movieObject movie) {
        this.movie = movie;
    }

    @Override
    public void onAttacheView(MovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        if (view != null) {
            Resources res = MovieApplication.getInstance().getResources();
            String title = movie.getTitle();
            String date = String.format(res.getString(R.string.release_date), movie.getReleaseDate());
            String url = movie.getPosterPath();
            String rating = String.format(res.getString(R.string.rating), Config.ratingFormat.format(movie.getVoteAverage()));

            view.fillHeader(url, title, rating, date);

            String overview = movie.getOverview();
            view.fillOverview(overview);
        }
    }

    @Override
    public void onDetachView() {
        view = null;
    }
}
