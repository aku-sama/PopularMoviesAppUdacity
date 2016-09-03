package raspopova.diana.popularmoviesapp.ui;

/**
 * Created by Diana on 9/3/2016.
 */
public interface IGeneralView {

    void showProgress();

    void hideProgress();

    void showError(String error, int... code);

    void showError(int error, int... code);
}
