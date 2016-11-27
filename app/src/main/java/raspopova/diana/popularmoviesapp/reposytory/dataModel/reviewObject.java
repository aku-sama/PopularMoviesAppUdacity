package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import raspopova.diana.popularmoviesapp.reposytory.server.Urls;

/**
 * Created by Diana on 9/3/2016.
 */
public class reviewObject implements Serializable {

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
}
