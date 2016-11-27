package raspopova.diana.popularmoviesapp.reposytory;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.trailerListObject;
import raspopova.diana.popularmoviesapp.reposytory.server.ServerMethods;
import raspopova.diana.popularmoviesapp.ui.moviDetails.IMovieDetailsInteractor;
import raspopova.diana.popularmoviesapp.ui.moviesList.IMovieInteractor;
import raspopova.diana.popularmoviesapp.ui.reviews.IReviewsInteractor;
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
     * @param page     - current page of list
     * @param listener
     */
    public static void getPopularMovie(long page, final IMovieInteractor.onMovieGetListener listener) {
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
     * @param page     - current page of list
     * @param listener
     */
    public static void getTopRatedMovie(long page, final IMovieInteractor.onMovieGetListener listener) {
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


    /**
     * GET REVIEWS LIST FOR MOVIE
     *
     * @param page
     * @param movieId
     * @param listener
     */
    public static void getMovieReviews(int page, String movieId, final IReviewsInteractor.onReviewsGetListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getReviewsForMovie(movieId, page, new Callback<reviewListObject>() {
                @Override
                public void onResponse(Call<reviewListObject> call, Response<reviewListObject> response) {

                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<reviewListObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(R.string.http_no_internet);
        }
    }

    /**
     *  GET TRAILERS FOR MOVIE
     *
     * @param movieId
     * @param listener
     */
    public static void getMovieTrailers(String movieId, final IMovieDetailsInteractor.onTrailersGetListener listener) {
        if (OnlineChecker.isOnline()) {
            ServerMethods.getTrailersForMovie(movieId, new Callback<trailerListObject>() {
                @Override
                public void onResponse(Call<trailerListObject> call, Response<trailerListObject> response) {

                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError(response.message(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<trailerListObject> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        } else {
            listener.onError(R.string.http_no_internet);
        }
    }
}
