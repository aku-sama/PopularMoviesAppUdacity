package raspopova.diana.popularmoviesapp.ui.moviesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.app.BundleConfig;
import raspopova.diana.popularmoviesapp.ui.GeneralActivity;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.favourites.FavouritesActivity;
import raspopova.diana.popularmoviesapp.ui.moviDetails.MovieDetailsActivity;
import raspopova.diana.popularmoviesapp.ui.settings.SettingsActivity;
import raspopova.diana.popularmoviesapp.utils.EndlessGridOnScrollListener;

/**
 * Created by Diana on 8/30/2016.
 */
public class MovieListActivity extends GeneralActivity implements IMovieView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.progressLayout)
    ProgressBar progressLayout;

    @BindView(R.id.moviesGridView)
    GridView movieGridView;


    private IMoviePresenter presenter;
    private MovieGridAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        ButterKnife.bind(this);

        adapter = new MovieGridAdapter(this);
        movieGridView.setAdapter(adapter);

        presenter = new MoviePresenter();
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(SettingsActivity.class);
                return true;
            case R.id.action_favourite:
                startActivity(FavouritesActivity.class);
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
        movieGridView.setOnScrollListener(listener);
        movieGridView.setOnItemClickListener(clickListener);

    }


    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
    }

    @Override
    public void fillMovieGrid(List<movieObject> result) {
        adapter.setData(result);
    }

    @Override
    public void showMovieDetails(movieObject item) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(BundleConfig.MOVIE, item);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error, int... code) {
        showErrorSnack(error, coordinatorLayout);
    }

    @Override
    public void showError(int error, int... code) {
        showErrorSnack(getString(error), coordinatorLayout);

    }

    EndlessGridOnScrollListener listener = new EndlessGridOnScrollListener() {
        @Override
        public void onLoadMore(int current_page) {
            presenter.getNewMoviePage(current_page);

        }
    };


    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onMoviePreviewClick(position);
        }
    };


}
