package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.app.Config;
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
            String title = movie.getTitle();
            String date = movie.getReleaseDate();
            String url = movie.getPosterPath();
            String rating = Config.ratingFormat.format(movie.getVoteAverage());

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
