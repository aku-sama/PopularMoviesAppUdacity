package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.reposytory.Repository;

/**
 * Created by Diana on 11/26/2016.
 */

public class MovieDetailsInteractor implements IMovieDetailsInteractor {
    @Override
    public void getTrailers(String movieId, onTrailersGetListener listener) {
        Repository.getMovieTrailers(movieId, listener);
    }
}
