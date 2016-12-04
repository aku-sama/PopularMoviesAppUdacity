package raspopova.diana.popularmoviesapp.repository.dataModel;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import raspopova.diana.popularmoviesapp.repository.dataBase.FavouriteColumns;
import raspopova.diana.popularmoviesapp.repository.server.Urls;

/**
 * Created by Diana on 9/3/2016.
 */
public class movieObject implements Parcelable {


    @SerializedName("id")
    private long id;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private double voteAverage;

    public movieObject() {
    }

    public movieObject(long id,
                       String title,
                       String posterPath,
                       String overview,
                       String releaseDate,
                       double voteAverage) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;

    }

    public long getId() {
        return id;
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

    public String getPurePosterPah(){
        return  posterPath;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public static movieObject fromCursor(Cursor cursor) {

        movieObject favourite = new movieObject(
                cursor.getLong(cursor.getColumnIndex(FavouriteColumns._ID)),
                cursor.getString(cursor.getColumnIndex(FavouriteColumns.TITLE)),
                cursor.getString(cursor.getColumnIndex(FavouriteColumns.POSTER_PATH)),
                cursor.getString(cursor.getColumnIndex(FavouriteColumns.OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(FavouriteColumns.RELEASE_DATE)),
                cursor.getDouble(cursor.getColumnIndex(FavouriteColumns.VOTE_AVERAGE)));

        return favourite;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeDouble(voteAverage);
    }

    public static final Parcelable.Creator<movieObject> CREATOR = new Parcelable.Creator<movieObject>() {
        public movieObject createFromParcel(Parcel in) {
            return new movieObject(in);
        }

        public movieObject[] newArray(int size) {
            return new movieObject[size];
        }
    };

    private movieObject(Parcel parcel) {
        id= parcel.readLong();
        title = parcel.readString();
        posterPath = parcel.readString();
        overview = parcel.readString();
        releaseDate = parcel.readString();
        voteAverage = parcel.readDouble();
    }
}
