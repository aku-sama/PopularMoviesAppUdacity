package raspopova.diana.popularmoviesapp.ui.reviews;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewListObject;

/**
 * Created by Diana on 11/27/2016.
 */

public class ReviewPresenter implements IReviewPersenter, IReviewsInteractor.onReviewsGetListener {

    public static final int START_PAGE = 1;
    private IReviewsInteractor interactor;
    private IReviewsView view;

    private String movieId;
    private reviewListObject reviews = new reviewListObject();

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
        if (view != null) {
            this.movieId = movieId;
            view.showEmptyState();
            getReview(START_PAGE);
        }
    }

    @Override
    public void getReview(int page) {
        if (view != null) {
            if (page == 1 || page < reviews.getTotalPages()) {
                view.showProgress();
                interactor.getReviews(page, movieId, this);
            } else if (page != 2 ) {
                view.showError(R.string.no_more_pages);
            }
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
            view.setReview(list.getResults());
        }
    }
}
