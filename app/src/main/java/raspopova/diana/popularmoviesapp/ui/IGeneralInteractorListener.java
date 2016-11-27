package raspopova.diana.popularmoviesapp.ui;

/**
 * Created by Diana on 11/26/2016.
 */
public interface IGeneralInteractorListener {

    void onError(String error, int... code);

    void onError(int errorId, int... code);
}
