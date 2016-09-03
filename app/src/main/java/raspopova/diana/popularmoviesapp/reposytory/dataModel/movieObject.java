package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

import raspopova.diana.popularmoviesapp.reposytory.server.Urls;

/**
 * Created by Diana on 9/3/2016.
 */
public class movieObject {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genre_ids")
    private Integer[] genreIds;

    @SerializedName("id")
    private long id;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("title")
    private String title;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private long voteCount;

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return Urls.BASE_URL_IMAGE + Urls.IMAGE_SIZE_ORIGINAL + backdropPath;
    }

    public List<Integer> getGenreIds() {
        return Arrays.asList(genreIds);
    }

    public long getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPathForPreview() {
        return Urls.BASE_URL_IMAGE + Urls.IMAGE_SIZE_185 + posterPath;
    }

    public String getPosterPath() {
        return Urls.BASE_URL_IMAGE + Urls.IMAGE_SIZE_ORIGINAL + posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
