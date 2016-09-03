package raspopova.diana.popularmoviesapp.reposytory;

import org.json.JSONObject;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.reposytory.server.ServerMethods;
import raspopova.diana.popularmoviesapp.ui.moviesList.MovieInteractor;
import raspopova.diana.popularmoviesapp.utils.OnlineChecker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diana on 9/3/2016.
 */
public class Repository {

    /**
     * GET MOST POPULAR
     *
     * @param page - current page of list
     * @param listener
     */
    public static void getPopularMovie(long page, final MovieInteractor.onMovieGetListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getPopularMovie(page, new Callback<movieListObject>() {
                @Override
                public void onResponse(Call<movieListObject> call, Response<movieListObject> response) {

                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<movieListObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(R.string.http_no_internet);
        }
    }

    /**
     * GET TOP RATED
     *
     * @param page - current page of list
     * @param listener
     */
    public static void getTopRatedMovie(long page, final MovieInteractor.onMovieGetListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getTopRatedMovie(page, new Callback<movieListObject>() {
                @Override
                public void onResponse(Call<movieListObject> call, Response<movieListObject> response) {

                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<movieListObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(R.string.http_no_internet);
        }
    }
}
