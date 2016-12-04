package raspopova.diana.popularmoviesapp.repository.dataBase;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by Diana on 11/28/2016.
 */

@ContentProvider(authority = FavouriteProvider.AUTHORITY, database = MovieDB.class)
public final class FavouriteProvider {

    public static final String AUTHORITY = "raspopova.diana.popularmoviesapp.reposytory.dataBase.FavouriteProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path{
        String FAVOURITES = "favourites";
    }

    private static Uri buildUri(String ... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths){
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = MovieDB.FAVOURITES)
    public static class Favourites {

        @ContentUri(
                path = Path.FAVOURITES,
                type = "vnd.android.cursor.dir/favourite",
                defaultSort = FavouriteColumns.TITLE + " ASC")
        public static final Uri  CONTENT_URI = buildUri(Path.FAVOURITES);

        @InexactContentUri(
                name = "FAVOURITE_ID",
                path = Path.FAVOURITES + "/#",
                type = "vnd.android.cursor.item/favourite",
                whereColumn = FavouriteColumns._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.FAVOURITES, String.valueOf(id));
        }
    }
}