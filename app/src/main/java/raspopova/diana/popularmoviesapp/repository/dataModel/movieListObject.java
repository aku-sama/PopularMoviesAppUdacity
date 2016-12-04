package raspopova.diana.popularmoviesapp.repository.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 9/3/2016.
 */
public class movieListObject implements Parcelable{

    @SerializedName("page")
    private long page;

    @SerializedName("results")
    private movieObject[] results;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    public long getPage() {
        return page;
    }

    public List<movieObject> getResults() {
        return Arrays.asList(results);
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public movieListObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeLong(page);
        parcel.writeLong(totalPages);
        parcel.writeLong(totalResults);
        parcel.writeTypedArray(results, flags);
    }

    public static final Parcelable.Creator<movieListObject> CREATOR = new Parcelable.Creator<movieListObject>() {
        public movieListObject createFromParcel(Parcel in) {
            return new movieListObject(in);
        }

        public movieListObject[] newArray(int size) {
            return new movieListObject[size];
        }
    };

    private movieListObject(Parcel parcel) {

        page = parcel.readLong();
        totalPages = parcel.readLong();
        totalResults = parcel.readLong();
        results = parcel.createTypedArray(movieObject.CREATOR);
    }
}
