package raspopova.diana.popularmoviesapp.reposytory.server;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diana on 9/3/2016.
 */

public class RetrofitForMyApp {

    private static Retrofit mRetrofit;
    private static Gson mGson;

    private static MovieServiceAPI service;

    private RetrofitForMyApp() {
        // no instances
    }

    public static void init() {
        mGson = new GsonBuilder()
                .create();

        OkHttpClient client = new OkHttpClient.Builder().build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

        service = mRetrofit.create(MovieServiceAPI.class);
    }


    public static MovieServiceAPI getRetrofitService() {
        if (service != null) {
            return service;
        } else {
            throw new IllegalStateException("mRetrofit not initialized");
        }
    }

}
