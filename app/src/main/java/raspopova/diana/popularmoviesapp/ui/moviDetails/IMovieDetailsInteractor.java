package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.trailerListObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralInteractorListener;

/**
 * Created by Diana on 11/26/2016.
 */

public interface IMovieDetailsInteractor {

    interface onTrailersGetListener extends IGeneralInteractorListener {
        void onSuccess(trailerListObject list);
    }

    void getTrailers(String movieId, onTrailersGetListener listener);
}
