package raspopova.diana.popularmoviesapp.ui.moviDetails;

import java.util.List;

import raspopova.diana.popularmoviesapp.reposytory.dataModel.trailerObject;
import raspopova.diana.popularmoviesapp.ui.IGeneralView;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IMovieDetailsView extends IGeneralView {

    void fillHeader(String posterUrl, String title, String rating, String releaseDate);

    void fillOverview(String overview);

    void fillReviewCount(String countText);

    void fillTrailerList(List<trailerObject> list);
}
