package raspopova.diana.popularmoviesapp.ui.moviDetails;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.repository.dataModel.trailerObject;

/**
 * Created by Diana on 11/26/2016.
 */

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {

    public static final String YOUTUBE_WATCH_URL = "http://www.youtube.com/watch?v=";

    List<trailerObject> list;
    LayoutInflater inflater;
    Context context;

    public MovieDetailsAdapter(List<trailerObject> list, Context context) {
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public MovieDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = inflater.inflate(R.layout.item_trailer, null);
        return new ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(MovieDetailsAdapter.ViewHolder holder, int position) {
        holder.trailerNameText.setText(list.get(position).getName());
        holder.trailerNameText.setTag(position);
        holder.trailerNameText.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            watchYoutubeVideo(list.get(position).getKey());
        }
    };

    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(YOUTUBE_WATCH_URL + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailerNameText)
        TextView trailerNameText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
