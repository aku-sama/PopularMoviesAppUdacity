package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import raspopova.diana.popularmoviesapp.reposytory.server.Urls;

/**
 * Created by Diana on 9/3/2016.
 */
public class reviewObject implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public reviewObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(url);
    }

    public static final Parcelable.Creator<reviewObject> CREATOR = new Parcelable.Creator<reviewObject>() {
        public reviewObject createFromParcel(Parcel in) {
            return new reviewObject(in);
        }

        public reviewObject[] newArray(int size) {
            return new reviewObject[size];
        }
    };

    private reviewObject(Parcel parcel) {

        id = parcel.readString();
        author = parcel.readString();
        content = parcel.readString();
        url = parcel.readString();
    }
}
