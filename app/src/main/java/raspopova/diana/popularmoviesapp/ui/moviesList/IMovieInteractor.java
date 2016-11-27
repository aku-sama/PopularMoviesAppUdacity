package raspopova.diana.popularmoviesapp.ui.moviesList;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralInteractorListener;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMovieInteractor {

    interface onMovieGetListener extends IGeneralInteractorListener {
        void onSuccess(movieListObject result);

    }

    void getTopRatedMovie(long page, onMovieGetListener listener);

    void getPopularMovie(long page, onMovieGetListener listener);
}
