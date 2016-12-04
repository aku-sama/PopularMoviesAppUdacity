package raspopova.diana.popularmoviesapp.ui.reviews;

import java.util.List;

import raspopova.diana.popularmoviesapp.repository.dataModel.reviewObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralView;

/**
 * Created by Diana on 11/27/2016.
 */

public interface IReviewsView extends IGeneralView {

    void setReview(List<reviewObject> list);

    void showEmptyState();

    void hideEmptyState();

    void setFirstVisiblePosition(int position);
}
