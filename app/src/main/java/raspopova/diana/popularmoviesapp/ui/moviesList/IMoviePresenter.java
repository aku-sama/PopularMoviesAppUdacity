package raspopova.diana.popularmoviesapp.ui.moviesList;

import java.util.List;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMoviePresenter extends IGeneralPresenter {

    void onAttacheView(IMovieView view);

    void initialize();

    void getNewMoviePage(int page);

    void onMoviePreviewClick(int position);

    List<movieObject> getMovieList();

    long getMoviePageCount();

    void restoreState(List<movieObject> movies, long totalPages, int firstVisiblePosition);
}
