package raspopova.diana.popularmoviesapp.ui.moviesList;

import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 9/3/2016.
 */
public interface MoviePresenter extends IGeneralPresenter {

    void onAttacheView(MovieView view);

    void initialize();

    void getNewMoviePage();

    void onMoviePreviewClick(int position);
}
