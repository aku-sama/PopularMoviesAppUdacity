package raspopova.diana.popularmoviesapp.app;

import android.app.Application;

import raspopova.diana.popularmoviesapp.reposytory.server.RetrofitForMyApp;

/**
 * Created by Diana on 9/3/2016.
 */
public class MovieApplication extends Application {

    private static MovieApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();
    }

    private void initRetrofit() {
        RetrofitForMyApp.init();
    }

    public static MovieApplication getInstance() {
        return instance;
    }
}
