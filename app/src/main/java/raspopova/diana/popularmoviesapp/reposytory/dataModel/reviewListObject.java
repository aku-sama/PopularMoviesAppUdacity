package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 11/26/2016.
 */

public class reviewListObject {

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

    public List<reviewObject> getResults() {
        return Arrays.asList(results);
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }
}
