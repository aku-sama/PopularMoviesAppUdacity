package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMovieDetailsPresenter extends IGeneralPresenter {

    void onAttacheView(IMovieDetailsView view);

    void initialize(movieObject movie);
}
