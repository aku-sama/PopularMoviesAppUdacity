package raspopova.diana.popularmoviesapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import raspopova.diana.popularmoviesapp.app.MovieApplication;

/**
 * Created by Diana on 9/3/2016.
 */
public class OnlineChecker {

    public static boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) MovieApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }
}
