package raspopova.diana.popularmoviesapp.ui.moviDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.GeneralActivity;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.customControls.TopCroppedImageView;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.posterView.PosterViewActivity;
import raspopova.diana.popularmoviesapp.ui.settings.SettingsActivity;

/**
 * Created by Diana on 8/30/2016.
 */
public class MovieDetailsActivity extends GeneralActivity implements MovieDetailsView {

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

    private movieObject movie;
    private MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            movie = (movieObject) getIntent().getSerializableExtra("movie");
        }
        presenter = new MovieDetailsPresenterImpl(movie);

        setSupportActionBar(toolbar);
        setTitle(movie.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view:
                Intent intent = new Intent(this, PosterViewActivity.class);
                intent.putExtra("poster", movie.getPosterPath());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttacheView(this);
        presenter.initialize();
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error, int... code) {
        showErrorSnack(error, coordinatorLayout);
    }

    @Override
    public void showError(int error, int... code) {
        showErrorSnack(getString(error), coordinatorLayout);
    }
}
