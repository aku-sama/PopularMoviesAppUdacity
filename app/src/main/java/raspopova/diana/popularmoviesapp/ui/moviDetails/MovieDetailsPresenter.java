package raspopova.diana.popularmoviesapp.ui.moviDetails;

import raspopova.diana.popularmoviesapp.ui.IGeneralPresenter;

/**
 * Created by Diana on 9/3/2016.
 */
public interface MovieDetailsPresenter extends IGeneralPresenter {

    void onAttacheView(MovieDetailsView view);

    void initialize();
}
