package raspopova.diana.popularmoviesapp.ui.moviesList;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;

/**
 * Created by Diana on 9/3/2016.
 */
public interface MovieInteractor {

    interface onMovieGetListener {
        void onSuccess(movieListObject result);

        void onError(String error, int... code);

        void onError(int error, int... code);
    }

    void getTopRatedMovie(long page, onMovieGetListener listener);

    void getPopularMovie(long page, onMovieGetListener listener);
}
