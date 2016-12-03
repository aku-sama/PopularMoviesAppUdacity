package raspopova.diana.popularmoviesapp.ui.favourites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.BundleConfig;
import raspopova.diana.popularmoviesapp.app.Config;
import raspopova.diana.popularmoviesapp.app.MovieApplication;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;
import raspopova.diana.popularmoviesapp.ui.moviDetails.MovieDetailsActivity;
import raspopova.diana.popularmoviesapp.utils.CursorRecyclerViewAdapter;

/**
 * Created by Diana on 11/27/2016.
 */

public class FavouritesCursorAdapter extends CursorRecyclerViewAdapter<FavouritesCursorAdapter.ViewHolder> {
    private Context context;

    public FavouritesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.posterImage)
        ImageView posterImage;

        @BindView(R.id.textMovieTitle)
        TextView textMovieTitle;

        @BindView(R.id.textRating)
        TextView textRating;

        @BindView(R.id.textReleaseDate)
        TextView textReleaseDate;

        @BindView(R.id.rootLayout)
        RelativeLayout rootLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourite, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        final movieObject myListItem = movieObject.fromCursor(cursor);
        viewHolder.textRating.setText(String.format(context.getString(R.string.rating), Config.ratingFormat.format(myListItem.getVoteAverage())));
        viewHolder.textReleaseDate.setText(String.format(MovieApplication.getInstance().getString(R.string.release_date), myListItem.getReleaseDate()));
        viewHolder.textMovieTitle.setText(myListItem.getTitle());

        if (Patterns.WEB_URL.matcher(myListItem.getPosterPathForPreview()).matches())
            Glide.with(context)
                    .load(myListItem.getPosterPathForPreview())
                    .into(viewHolder.posterImage)
                    .onLoadStarted(context.getResources().getDrawable(R.drawable.ic_poster_placeholder));


        viewHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra(BundleConfig.MOVIE, myListItem);
                context.startActivity(intent);
            }
        });
    }

}