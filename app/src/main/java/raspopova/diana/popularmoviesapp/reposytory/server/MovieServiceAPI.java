package raspopova.diana.popularmoviesapp.reposytory.server;


import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.trailerListObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Diana on 9/3/2016.
 */
public interface MovieServiceAPI {

    @GET(Urls.MOVIE_POPULAR)
    Call<movieListObject> getPopularMovie(@Query("api_key") String api_key,
                                          @Query("page") long page,
                                          @Query("language") String language);


    @GET(Urls.MOVIE_TOP_RATED)
    Call<movieListObject> getTopRatedMovie(@Query("api_key") String api_key,
                                           @Query("page") long page,
                                           @Query("language") String language);


    @GET("3/movie/{id}/videos")
    Call<trailerListObject> getMovieTrailers(@Path("id") String id,
                                             @Query("api_key") String api_key,
                                             @Query("language") String language);

    @GET("3/movie/{id}/reviews")
    Call<reviewListObject> getMovieReviews(@Path("id") String id,
                                           @Query("api_key") String api_key,
                                           @Query("page") long page,
                                           @Query("language") String language);
}
