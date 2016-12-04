package raspopova.diana.popularmoviesapp.ui.moviesList;

import java.util.List;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralView;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMovieView extends IGeneralView {

    void fillMovieGrid(List<movieObject> result);

    void showMovieDetails(movieObject item);

    void setLastVisiblePosition(int position);
}
