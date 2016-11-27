package raspopova.diana.popularmoviesapp.ui.reviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.customControls.ExpandableTextView;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.reviewObject;

/**
 * Created by Diana on 11/27/2016.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    List<reviewObject> list;
    LayoutInflater inflater;
    Context context;

    public ReviewsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<reviewObject> reviews) {
        int position = this.list.size();
        this.list.addAll(reviews);
        notifyItemRangeChanged(position, this.list.size());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = inflater.inflate(R.layout.item_review, null);
        return new ReviewsAdapter.ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userNameText.setText(this.list.get(position).getAuthor());
        holder.reviewContentText.setText(this.list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return this.list == null ? 0 : this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userNameText)
        TextView userNameText;

        @BindView(R.id.reviewContentText)
        ExpandableTextView reviewContentText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
