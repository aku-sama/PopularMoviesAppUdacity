package raspopova.diana.popularmoviesapp.repository.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diana on 9/3/2016.
 */
public class trailerObject implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("iso_639_1")
    private String iso_639_1;

    @SerializedName("iso_3166_1")
    private String iso_3166_1;

    @SerializedName("key")
    private String key;

    @SerializedName("site")
    private String site;

    @SerializedName("name")
    private String name;

    @SerializedName("size")
    private int size;

    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public trailerObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(iso_639_1);
        parcel.writeString(iso_3166_1);
        parcel.writeString(key);
        parcel.writeString(site);
        parcel.writeString(name);
        parcel.writeInt(size);
        parcel.writeString(type);
    }

    public static final Parcelable.Creator<trailerObject> CREATOR = new Parcelable.Creator<trailerObject>() {
        public trailerObject createFromParcel(Parcel in) {
            return new trailerObject(in);
        }

        public trailerObject[] newArray(int size) {
            return new trailerObject[size];
        }
    };

    private trailerObject(Parcel parcel) {
        id = parcel.readString();
        iso_639_1 = parcel.readString();
        iso_3166_1 = parcel.readString();
        key = parcel.readString();
        site = parcel.readString();
        name = parcel.readString();
        size = parcel.readInt();
        type = parcel.readString();
    }
}
