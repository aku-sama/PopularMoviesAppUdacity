package raspopova.diana.popularmoviesapp.reposytory.dataBase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Diana on 11/28/2016.
 */

@Database(version = MovieDB.VERSION)
public final class MovieDB {

    private MovieDB(){}

    public static final int VERSION = 1;

    @Table(FavouriteColumns.class)
    public static final String FAVOURITES = "favourites";
}
