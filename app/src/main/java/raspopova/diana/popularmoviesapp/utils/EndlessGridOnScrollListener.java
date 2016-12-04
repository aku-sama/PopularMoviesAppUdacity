package raspopova.diana.popularmoviesapp.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by Diana on 11/1/2016.
 */

public abstract class EndlessGridOnScrollListener implements AbsListView.OnScrollListener {
    public static String TAG = EndlessGridOnScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 9; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;


    public EndlessGridOnScrollListener() {
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //visibleItemCount = absListView.getChildCount();
       // totalItemCount = mLinearLayoutManager.getAdapter().getCount();
       // firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            current_page++;

            onLoadMore(current_page);

            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public abstract void onLoadMore(int current_page);

    public void setCurrent_page(int value) {
        current_page = value;
    }
    public int getCurrent_page() {
       return current_page ;
    }
}