package raspopova.diana.popularmoviesapp.ui.reviews;

import java.util.ArrayList;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewObject;

/**
 * Created by Diana on 11/27/2016.
 */

public class ReviewPresenter implements IReviewPersenter, IReviewsInteractor.onReviewsGetListener {

    public static final int START_PAGE = 1;
    private IReviewsInteractor interactor;
    private IReviewsView view;

    private String movieId;
    private reviewListObject reviews = new reviewListObject();
    boolean isRestored = false;
    boolean isAlreadyInit = false;

    public ReviewPresenter() {
        this.interactor = new ReviewsInteractor();
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

    @Override
    public void onAttacheView(IReviewsView view) {
        this.view = view;
    }

    @Override
    public void initialize(String movieId) {
        if (view != null && !isRestored && !isAlreadyInit) {
            this.movieId = movieId;
            view.showEmptyState();
            getReview(START_PAGE);
            isAlreadyInit = true;
        }
    }

    @Override
    public void getReview(int page) {
        if (view != null) {
            if (page == 1 || page < reviews.getTotalPages()) {
                view.showProgress();
                interactor.getReviews(page, movieId, this);
            }
        }
    }

    @Override
    public String getMovieID() {
        return movieId;
    }

    @Override
    public reviewListObject getReviewList() {
        return reviews;
    }

    @Override
    public void restoreState(String movieID, reviewListObject reviews, int firstVisiblePosition) {
        this.movieId = movieID;
        this.reviews = reviews;
        isRestored = true;
        if (view != null) {
            view.setReview(reviews.getResults());
            view.setFirstVisiblePosition(firstVisiblePosition);
        }
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    @Override
    public void onSuccess(reviewListObject list) {
        if (view != null) {
            view.hideProgress();

            if (this.reviews.getResults().size() == 0) {
                this.reviews = list;
            } else {
                this.reviews.setPage(list.getPage());
                this.reviews.appendResults(new ArrayList<reviewObject>(list.getResults()));
            }
            view.setReview(list.getResults());
        }
    }
}
