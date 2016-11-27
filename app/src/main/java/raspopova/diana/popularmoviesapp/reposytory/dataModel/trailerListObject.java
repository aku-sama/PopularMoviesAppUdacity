package raspopova.diana.popularmoviesapp.reposytory.dataModel;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Diana on 11/26/2016.
 */

public class trailerListObject {

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

}
