package raspopova.diana.popularmoviesapp.ui.reviews;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralInteractorListener;

/**
 * Created by Diana on 11/26/2016.
 */

public interface IReviewsInteractor {

    interface onReviewsGetListener extends IGeneralInteractorListener {
        void onSuccess(reviewListObject list);
    }

    void getReviews(int page, String movieId, onReviewsGetListener listener);
}
