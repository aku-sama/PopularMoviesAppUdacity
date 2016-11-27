package raspopova.diana.popularmoviesapp.ui.reviews;

import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 11/27/2016.
 */

public interface IReviewPersenter extends IGeneralPresenter {
    void onAttacheView(IReviewsView view);

    void initialize(String movieId);

    void getReview(int page);
}
