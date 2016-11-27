package raspopova.diana.popularmoviesapp.ui.moviesList;

import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMoviePresenter extends IGeneralPresenter {

    void onAttacheView(IMovieView view);

    void initialize();

    void getNewMoviePage(int page);

    void onMoviePreviewClick(int position);
}
