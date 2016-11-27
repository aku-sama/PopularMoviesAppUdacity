package raspopova.diana.popularmoviesapp.ui.reviews;

import raspopova.diana.popularmoviesapp.reposytory.Repository;

/**
 * Created by Diana on 11/26/2016.
 */

public class ReviewsInteractor implements IReviewsInteractor {
    @Override
    public void getReviews(int page, String movieId, onReviewsGetListener listener) {
        Repository.getMovieReviews(page, movieId, listener);
    }
}
