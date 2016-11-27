package raspopova.diana.popularmoviesapp.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.MovieApplication;

/**
 * Created by Diana on 9/3/2016.
 */
public class LocaleHelper {

    public static String getLocale() {
        String ENGLISH_LOCALE = "0";
        String currentLanguage;

        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(MovieApplication.getInstance());
        currentLanguage = pref.getString(MovieApplication.getInstance().getString(R.string.pref_language_key),
                MovieApplication.getInstance().getString(R.string.pref_language_default_value));

        if (currentLanguage.equals(ENGLISH_LOCALE))
            return "En_en";
        else
            return Locale.getDefault().toString();
    }
}
