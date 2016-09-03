package raspopova.diana.popularmoviesapp.ui.moviesList;

import raspopova.diana.popularmoviesapp.reposytory.Repository;

/**
 * Created by Diana on 9/3/2016.
 */

public class MovieInteractorImpl implements MovieInteractor {

    @Override
    public void getTopRatedMovie(long page, onMovieGetListener listener) {
        Repository.getTopRatedMovie(page, listener);
    }

    @Override
    public void getPopularMovie(long page, onMovieGetListener listener) {
        Repository.getPopularMovie(page, listener);
    }
}
