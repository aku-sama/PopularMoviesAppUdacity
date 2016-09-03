package raspopova.diana.popularmoviesapp.reposytory.server;


import raspopova.diana.popularmoviesapp.app.ApiKey;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.utils.LocaleHelper;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Diana on 9/3/2016.
 */
public class ServerMethods {


    /**
     * GET LIST SORTED BY POPULAR
     *
     * @param currentPage
     * @param cb
     */
    public static void getPopularMovie(long currentPage,
                                       Callback<movieListObject> cb) {

        Call<movieListObject> call = RetrofitForMyApp.getRetrofitService()
                .getPopularMovie(ApiKey.API_KEY, currentPage, LocaleHelper.getLocale());

        call.enqueue(cb);
    }


    /**
     * GET LIST SORTED BY RATING
     *
     * @param currentPage
     * @param cb
     */
    public static void getTopRatedMovie(long currentPage,
                                        Callback<movieListObject> cb) {

        Call<movieListObject> call = RetrofitForMyApp.getRetrofitService()
                .getTopRatedMovie(ApiKey.API_KEY, currentPage, LocaleHelper.getLocale());

        call.enqueue(cb);
    }


}
