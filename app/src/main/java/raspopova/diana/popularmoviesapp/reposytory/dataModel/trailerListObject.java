package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 11/26/2016.
 */

public class trailerListObject implements  Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("results")
    private trailerObject[] results;


    public long getId() {
        return id;
    }

    public List<trailerObject> getResults() {
        return Arrays.asList(results);
    }

    public trailerListObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeLong(id);
        parcel.writeTypedArray(results, flags);
    }

    public static final Parcelable.Creator<trailerListObject> CREATOR = new Parcelable.Creator<trailerListObject>() {
        public trailerListObject createFromParcel(Parcel in) {
            return new trailerListObject(in);
        }

        public trailerListObject[] newArray(int size) {
            return new trailerListObject[size];
        }
    };

    private trailerListObject(Parcel parcel) {

        id = parcel.readLong();
        results = parcel.createTypedArray(trailerObject.CREATOR);
    }
}
