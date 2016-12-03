package raspopova.diana.popularmoviesapp.reposytory.dataBase;

import android.support.annotation.NonNull;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.REAL;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;
import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;

/**
 * Created by Diana on 11/28/2016.
 */

public interface FavouriteColumns {
    @DataType(INTEGER)
    @PrimaryKey  @NotNull
    String _ID = "_id";

    @DataType(TEXT)
    @NotNull
    String TITLE = "title";

    @DataType(TEXT)
    @NotNull
    String POSTER_PATH = "poster_path";

    @DataType(TEXT)
    @NotNull
    String OVERVIEW = "overview";

    @DataType(TEXT)
    @NotNull
    String RELEASE_DATE = "release_date";

    @DataType(REAL)
    @NotNull
    String VOTE_AVERAGE = "vote_average";

}
