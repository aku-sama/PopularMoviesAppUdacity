package raspopova.diana.popularmoviesapp.repository.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 11/26/2016.
 */

public class reviewListObject implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("page")
    private long page;

    @SerializedName("results")
    private reviewObject[] results;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    public long getId() {
        return id;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<reviewObject> getResults() {
        return results == null ? new ArrayList<reviewObject>() : Arrays.asList(results);
    }

    public void appendResults(ArrayList<reviewObject> appendResults) {
        ArrayList<reviewObject> currentResults = new ArrayList<>();
        currentResults.addAll(getResults());
        currentResults.addAll(appendResults);
        results = new reviewObject[currentResults.size()];
        currentResults.toArray(results);
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public reviewListObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeLong(id);
        parcel.writeLong(page);
        parcel.writeLong(totalPages);
        parcel.writeLong(totalResults);
        parcel.writeTypedArray(results, flags);
    }

    public static final Parcelable.Creator<reviewListObject> CREATOR = new Parcelable.Creator<reviewListObject>() {
        public reviewListObject createFromParcel(Parcel in) {
            return new reviewListObject(in);
        }

        public reviewListObject[] newArray(int size) {
            return new reviewListObject[size];
        }
    };

    private reviewListObject(Parcel parcel) {

        id = parcel.readLong();
        page = parcel.readLong();
        totalPages = parcel.readLong();
        totalResults = parcel.readLong();
        results = parcel.createTypedArray(reviewObject.CREATOR);
    }
}
