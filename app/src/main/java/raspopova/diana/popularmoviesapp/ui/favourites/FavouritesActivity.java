package raspopova.diana.popularmoviesapp.ui.favourites;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataBase.FavouriteProvider;
import raspopova.diana.popularmoviesapp.ui.GeneralActivity;

/**
 * Created by Diana on 11/27/2016.
 */

public class FavouritesActivity extends GeneralActivity {

    @BindView(R.id.emptyFavouritesLayout)
    RelativeLayout emptyFavouritesLayout;

    @BindView(R.id.favouriteList)
    RecyclerView favouriteList;

    @BindView(R.id.progressView)
    ProgressBar progressView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private FavouritesCursorAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        ButterKnife.bind(this);
        setToolbar();


    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        favouriteList.setLayoutManager(new LinearLayoutManager(this));
        cursor = getContentResolver().query(FavouriteProvider.Favourites.CONTENT_URI,
                null, null, null, null);

        adapter = new FavouritesCursorAdapter(this, cursor);
        favouriteList.setAdapter(adapter);

        if (cursor.getCount() > 0)
            emptyFavouritesLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cursor != null)
            cursor.close();
    }
}
