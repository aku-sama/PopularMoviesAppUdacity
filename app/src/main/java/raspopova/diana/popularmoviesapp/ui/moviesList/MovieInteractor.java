package raspopova.diana.popularmoviesapp.ui.moviesList;

import raspopova.diana.popularmoviesapp.repository.Repository;

/**
 * Created by Diana on 9/3/2016.
 */

public class MovieInteractor implements IMovieInteractor {

    @Override
    public void getTopRatedMovie(long page, onMovieGetListener listener) {
        Repository.getTopRatedMovie(page, listener);
    }

    @Override
    public void getPopularMovie(long page, onMovieGetListener listener) {
        Repository.getPopularMovie(page, listener);
    }
}
