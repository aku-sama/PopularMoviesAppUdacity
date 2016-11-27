package raspopova.diana.popularmoviesapp.ui.reviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.BundleConfig;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewObject;
import raspopova.diana.popularmoviesapp.ui.GeneralActivity;
import raspopova.diana.popularmoviesapp.utils.EndlessGridOnScrollListener;
import raspopova.diana.popularmoviesapp.utils.EndlessRecyclerOnScrollListener;

/**
 * Created by Diana on 11/26/2016.
 */

public class ReviewsActivity extends GeneralActivity implements IReviewsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.emptyReviewsLayout)
    RelativeLayout emptyReviewsLayout;

    @BindView(R.id.reviewList)
    RecyclerView reviewList;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.progressView)
    ProgressBar progressView;

    private ReviewsAdapter adapter;
    private IReviewPersenter presenter;
    private String movieId = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        ButterKnife.bind(this);

        presenter = new ReviewPresenter();
        //set toolbar
        setToolbar();

        //init review list
        initReviewList();

        if (getIntent().getExtras() != null) {
            movieId = getIntent().getStringExtra(BundleConfig.MOVIE_ID);
        }
    }

    private void initReviewList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ReviewsAdapter(this);
        reviewList.setLayoutManager(layoutManager);
        reviewList.setAdapter(adapter);
        reviewList.setOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                presenter.getReview(current_page);
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttacheView(this);
        presenter.initialize(movieId);
    }

    @Override
    protected void onStop() {
        presenter.onDetachView();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BundleConfig.MOVIE_ID, movieId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movieId = savedInstanceState.getString(BundleConfig.MOVIE_ID);
    }

    @Override
    public void setReview(List<reviewObject> list) {
        adapter.setData(list);

        if(adapter.getItemCount()>0)
            hideEmptyState();
        else
            showEmptyState();
    }

    @Override
    public void showEmptyState() {
        emptyReviewsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyState() {
        emptyReviewsLayout.setVisibility(View.GONE);
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
}
