package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 9/3/2016.
 */
public class movieListObject {

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
}
