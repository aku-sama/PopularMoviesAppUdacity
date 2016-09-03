package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.ui.IGeneralView;

/**
 * Created by Diana on 9/3/2016.
 */
public interface MovieDetailsView extends IGeneralView {

    void fillHeader(String posterUrl, String title, String rating, String releaseDate);

    void fillOverview(String overview);
}
