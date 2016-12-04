package raspopova.diana.popularmoviesapp.ui.moviDetails;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.util.Log;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.Config;
import raspopova.diana.popularmoviesapp.app.MovieApplication;
import raspopova.diana.popularmoviesapp.reposytory.dataBase.FavouriteColumns;
import raspopova.diana.popularmoviesapp.reposytory.dataBase.FavouriteProvider;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.trailerListObject;
import raspopova.diana.popularmoviesapp.ui.reviews.IReviewsInteractor;
import raspopova.diana.popularmoviesapp.ui.reviews.ReviewsInteractor;

/**
 * Created by Diana on 9/3/2016.
 */
public class MovieDetailsPresenter implements IMovieDetailsPresenter, IMovieDetailsInteractor.onTrailersGetListener,
        IReviewsInteractor.onReviewsGetListener {

    private final String LOG_TAG = "CURSOR";

    private IMovieDetailsView view;
    private IMovieDetailsInteractor interactor;
    private IReviewsInteractor reviewsInteractor;

    private movieObject movie;
    private trailerListObject trailerList;
    private reviewListObject reviewFirstPage;

    public MovieDetailsPresenter() {
        this.interactor = new MovieDetailsInteractor();
        this.reviewsInteractor = new ReviewsInteractor();
    }

    @Override
    public void onAttacheView(IMovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void initialize(movieObject movie) {
        this.movie = movie;
        if (view != null) {
            //fill header
            fillHeader(movie);

            //fill default review by 0 and request 1st review page
            view.fillReviewCount(String.format(MovieApplication.getInstance().getString(R.string.review_count), "0"));
            reviewsInteractor.getReviews(1, String.valueOf(movie.getId()), this);

            //get trailer trailerList
            view.showProgress();
            interactor.getTrailers(String.valueOf(movie.getId()), this);

            //check favourite status
            view.changeFavouriteStatus(isFavourite());
        }
    }

    private void fillHeader(movieObject movie) {
        Resources res = MovieApplication.getInstance().getResources();
        String title = movie.getTitle();
        String date = String.format(res.getString(R.string.release_date), movie.getReleaseDate());
        String url = movie.getPosterPath();
        String rating = String.format(res.getString(R.string.rating), Config.ratingFormat.format(movie.getVoteAverage()));

        view.fillHeader(url, title, rating, date);

        //fill Overview
        String overview = movie.getOverview();
        view.fillOverview(overview);
    }

    @Override
    public void changeFavouriteStatus() {
        if (isFavourite())
            removeFromFavourite();
        else
            insertToFavourite();

    }

    @Override
    public movieObject getMovie() {
        return movie;
    }

    @Override
    public trailerListObject getTrailerList() {
        return trailerList;
    }

    @Override
    public reviewListObject getReviewList() {
        return reviewFirstPage;
    }

    @Override
    public void restoreState(movieObject movie, trailerListObject trailers, reviewListObject reviews) {
        this.movie = movie;
        this.reviewFirstPage = reviews;
        this.trailerList = trailers;
        if(view!=null)
        {
            fillHeader(movie);
            view.fillTrailerList(this.trailerList.getResults());
            view.fillReviewCount(String.format(MovieApplication.getInstance().getString(R.string.review_count),
                    String.valueOf(reviewFirstPage.getTotalResults())));
            //check favourite status
            view.changeFavouriteStatus(isFavourite());
        }
    }

    private boolean isFavourite() {
        //check favourite status, if no in cursor = no in fav
        Cursor c = MovieApplication.getInstance().getContentResolver().query(FavouriteProvider.Favourites.withId(movie.getId()),
                null, null, null, null);
        if (c == null || c.getCount() == 0) {
            return false;
        } else {
            c.close();
            return true;
        }
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void onSuccess(trailerListObject list) {
        if (view != null) {
            this.trailerList = list;
            view.hideProgress();
            view.fillTrailerList(this.trailerList.getResults());
        }
    }

    @Override
    public void onSuccess(reviewListObject list) {
        if (view != null) {
            reviewFirstPage = list;
            view.hideProgress();
            view.fillReviewCount(String.format(MovieApplication.getInstance().getString(R.string.review_count),
                    String.valueOf(reviewFirstPage.getTotalResults())));

        }
    }

    @Override
    public void onError(String error, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(error, code);
        }
    }

    @Override
    public void onError(int errorId, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(errorId, code);
        }
    }

    private void insertToFavourite() {
        Log.d(LOG_TAG, "insert");
        ContentValues cv = new ContentValues();
        cv.put(FavouriteColumns._ID, movie.getId());
        cv.put(FavouriteColumns.OVERVIEW, movie.getOverview());
        cv.put(FavouriteColumns.POSTER_PATH, movie.getPurePosterPah());
        cv.put(FavouriteColumns.RELEASE_DATE, movie.getReleaseDate());
        cv.put(FavouriteColumns.TITLE, movie.getTitle());
        cv.put(FavouriteColumns.VOTE_AVERAGE, movie.getVoteAverage());
        MovieApplication.getInstance().getContentResolver().delete(FavouriteProvider.Favourites.withId(movie.getId()),
                null, null);
        MovieApplication.getInstance().getContentResolver().insert(FavouriteProvider.Favourites.withId(movie.getId()), cv);
        if (view != null) {
            view.changeFavouriteStatus(true);
        }

    }

    private void removeFromFavourite() {
        Log.d(LOG_TAG, "delete");

        MovieApplication.getInstance().getContentResolver().delete(FavouriteProvider.Favourites.withId(movie.getId()),
                null, null);

        if (view != null) {
            view.changeFavouriteStatus(false);
        }
    }
}
