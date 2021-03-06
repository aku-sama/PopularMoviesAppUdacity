package raspopova.diana.popularmoviesapp.ui.moviDetails;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import raspopova.diana.popularmoviesapp.app.BundleConfig;
import raspopova.diana.popularmoviesapp.repository.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.repository.dataModel.trailerListObject;
import raspopova.diana.popularmoviesapp.repository.dataModel.trailerObject;
import raspopova.diana.popularmoviesapp.ui.GeneralActivity;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.customControls.TopCroppedImageView;
import raspopova.diana.popularmoviesapp.repository.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.posterView.PosterViewActivity;
import raspopova.diana.popularmoviesapp.ui.reviews.ReviewsActivity;

/**
 * Created by Diana on 8/30/2016.
 */
public class MovieDetailsActivity extends GeneralActivity implements IMovieDetailsView {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imagePoster)
    TopCroppedImageView posterImage;

    @BindView(R.id.textMovieTitle)
    TextView movieTitleText;

    @BindView(R.id.textRating)
    TextView ratingText;

    @BindView(R.id.textReleaseDate)
    TextView releaseDateText;

    @BindView(R.id.textOverview)
    TextView overviewText;

    @BindView(R.id.imageViewPoster)
    ImageView posterViewImage;

    @BindView(R.id.imageBackButton)
    ImageView backButtonImage;

    @BindView(R.id.reviewText)
    TextView reviewText;

    @BindView(R.id.trailerList)
    RecyclerView trailerList;

    @BindView(R.id.progressView)
    ProgressBar progressView;

    @BindView(R.id.fabFavourite)
    FloatingActionButton fabFavourite;

    private movieObject movie;
    private IMovieDetailsPresenter presenter;
    private MovieDetailsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            movie = getIntent().getParcelableExtra(BundleConfig.MOVIE);
            saveToPref(movie);
        } else
            movie = getFromPref();

        presenter = new MovieDetailsPresenter();

        setToolbar();

        presenter.onAttacheView(this);
        if (movie != null)
            presenter.initialize(movie);
        else
            showError(getString(R.string.error_lost_movie));
    }

    private movieObject getFromPref() {
        Gson gson = new Gson();
        String movieString =
                PreferenceManager.getDefaultSharedPreferences(this).getString(BundleConfig.MOVIE, "");
        movieObject lastMovie = gson.fromJson(movieString, movieObject.class);
        return lastMovie;
    }

    private void saveToPref(movieObject movie) {
        Gson gson = new Gson();
        String movieString = gson.toJson(movie, movieObject.class);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(BundleConfig.MOVIE, movieString).apply();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetachView();
        super.onDestroy();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.imageBackButton)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.imageViewPoster)
    void onPosterView() {
        Intent intent = new Intent(this, PosterViewActivity.class);
        intent.putExtra("poster", movie.getPosterPath());
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BundleConfig.MOVIE, presenter.getMovie());
        outState.putParcelable(BundleConfig.MOVIE_TRAILERS, presenter.getTrailerList());
        outState.putParcelable(BundleConfig.MOVIE_REVIEWS_FIRST_PAGE, presenter.getReviewList());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movie = savedInstanceState.getParcelable(BundleConfig.MOVIE);
        trailerListObject trailers = savedInstanceState.getParcelable(BundleConfig.MOVIE_TRAILERS);
        reviewListObject reviews = savedInstanceState.getParcelable(BundleConfig.MOVIE_REVIEWS_FIRST_PAGE);
        presenter.restoreState(movie, trailers, reviews);
    }


    @Override
    public void fillHeader(String posterUrl, String title, String rating, String releaseDate) {
        movieTitleText.setText(title);
        ratingText.setText(rating);
        releaseDateText.setText(releaseDate);
        Glide.with(this).load(posterUrl).into(posterImage);
    }

    @Override
    public void fillOverview(String overview) {
        overviewText.setText(overview);
    }

    @Override
    public void fillReviewCount(String countText) {
        reviewText.setText(countText);
    }

    @Override
    public void fillTrailerList(List<trailerObject> list) {
        adapter = new MovieDetailsAdapter(list, this);
        trailerList.setAdapter(adapter);
    }

    @Override
    public void changeFavouriteStatus(boolean isFavourite) {
        fabFavourite.setImageResource(isFavourite ? R.drawable.ic_fav_active : R.drawable.ic_fav_unactive);
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error, int... code) {
        showErrorSnack(error, coordinatorLayout);
    }

    @Override
    public void showError(int error, int... code) {
        showErrorSnack(getString(error), coordinatorLayout);
    }

    @OnClick(R.id.reviewText)
    void onReviewsClick() {
        Intent intent = new Intent(this, ReviewsActivity.class);
        intent.putExtra(BundleConfig.REVIEWS_MOVIE_ID, String.valueOf(movie.getId()));
        startActivity(intent);
    }

    @OnClick(R.id.fabFavourite)
    void onFabClick() {
        presenter.changeFavouriteStatus();
    }


}
