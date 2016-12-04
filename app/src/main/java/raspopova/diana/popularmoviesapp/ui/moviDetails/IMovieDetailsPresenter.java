package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.repository.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.repository.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.repository.dataModel.trailerListObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMovieDetailsPresenter extends IGeneralPresenter {

    void onAttacheView(IMovieDetailsView view);

    void initialize(movieObject movie);

    void changeFavouriteStatus();

    movieObject getMovie();

    trailerListObject getTrailerList();

    reviewListObject getReviewList();

    void restoreState(movieObject movie, trailerListObject trailers, reviewListObject reviews);
}
