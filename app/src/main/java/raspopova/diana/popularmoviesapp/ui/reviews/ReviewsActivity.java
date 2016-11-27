package raspopova.diana.popularmoviesapp.ui.reviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.ui.GeneralActivity;

/**
 * Created by Diana on 11/26/2016.
 */

public class ReviewsActivity extends GeneralActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.emptyReviewsLayout)
    RelativeLayout emptyReviewsLayout;

    @BindView(R.id.reviewList)
    RecyclerView reviewList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        ButterKnife.bind(this);

        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
