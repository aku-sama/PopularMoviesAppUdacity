package raspopova.diana.popularmoviesapp.ui.moviesList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.Config;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;

/**
 * Created by Diana on 9/3/2016.
 */
public class MovieGridAdapter extends BaseAdapter {

    private Context context;
    private List<movieObject> items;
    private static LayoutInflater inflater = null;

    public MovieGridAdapter(Context context, List<movieObject> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View vi = convertView;

        if(convertView == null) {
            vi = inflater.inflate(R.layout.item_movie, null);

            viewHolder = new ViewHolder();

            viewHolder.posterImage = (ImageView)vi.findViewById(R.id.imagePoster);
            viewHolder.ratingText = (TextView)vi.findViewById(R.id.textRating);
            vi.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) vi.getTag();
        }

        Glide.with(context).load(items.get(position).getPosterPathForPreview()).into(viewHolder.posterImage);
        viewHolder.ratingText.setText(Config.ratingFormat.format(items.get(position).getVoteAverage()));

        return vi;

    }

    static class ViewHolder {
        ImageView posterImage;
        TextView ratingText;
    }
}