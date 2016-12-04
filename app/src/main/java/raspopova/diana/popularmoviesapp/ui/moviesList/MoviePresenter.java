package raspopova.diana.popularmoviesapp.ui.moviesList;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.MovieApplication;
import raspopova.diana.popularmoviesapp.repository.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.repository.dataModel.movieObject;

/**
 * Created by Diana on 9/3/2016.
 */
public class MoviePresenter implements IMoviePresenter, IMovieInteractor.onMovieGetListener {

    public static final String POPULAR_SORT_ORDER = "0";

    private IMovieInteractor interactor;
    private IMovieView view;

    private long firstPage = 1;
    private long endPage = 2;
    private List<movieObject> movieList;
    private String currentOrdering = POPULAR_SORT_ORDER;
    boolean isAlreadyInit = false;

    public MoviePresenter() {
        interactor = new MovieInteractor();
        movieList = new ArrayList<>();
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(MovieApplication.getInstance());
        currentOrdering = pref.getString(MovieApplication.getInstance().getString(R.string.pref_sort_key),
                MovieApplication.getInstance().getString(R.string.pref_default_value));
    }

    @Override
    public void onAttacheView(IMovieView view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        if (view != null && !isAlreadyInit) {
            view.showProgress();
            isAlreadyInit = true;

            if (currentOrdering.equals(POPULAR_SORT_ORDER))
                interactor.getPopularMovie(firstPage, this);
            else
                interactor.getTopRatedMovie(firstPage, this);
        }
    }

    @Override
    public void getNewMoviePage(int currentPage) {
        if (view != null) {
            if (currentPage < endPage) {
                view.showProgress();
                Log.d("current_page", String.valueOf(currentPage));
                if (currentOrdering.equals(POPULAR_SORT_ORDER))
                    interactor.getPopularMovie(currentPage, this);
                else
                    interactor.getTopRatedMovie(currentPage, this);

            } else {
                view.showError(R.string.no_more_pages);
            }
        }
    }

    @Override
    public void checkSortOrder() {
        if (view != null) {

            //check ordering change
            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(MovieApplication.getInstance());
            String sortOrder = pref.getString(MovieApplication.getInstance().getString(R.string.pref_sort_key),
                    MovieApplication.getInstance().getString(R.string.pref_default_value));

            //if order was changed, reset list
            if (!currentOrdering.equals(sortOrder)) {
                movieList.clear();
                endPage = 2;
                currentOrdering = sortOrder;

                isAlreadyInit = false;
                view.updateGridView();
                initialize();
            }


        }
    }

    @Override
    public void onMoviePreviewClick(int position) {
        if (view != null) {
            view.showMovieDetails(movieList.get(position));
        }
    }

    @Override
    public List<movieObject> getMovieList() {
        return movieList;
    }

    @Override
    public long getMoviePageCount() {
        return endPage;
    }

    @Override
    public void restoreState(List<movieObject> movies, long totalPages, int firstVisiblePosition) {
        endPage = totalPages;
        this.movieList = new ArrayList<>(movies);
        if (view != null) {
            view.fillMovieGrid(movieList);
            view.setFirstVisiblePosition(firstVisiblePosition);
        }
    }


    @Override
    public void onSuccess(movieListObject result) {
        if (view != null) {
            view.hideProgress();
            movieList.addAll(result.getResults());
            endPage = result.getTotalPages();

            view.fillMovieGrid(movieList);
        }
    }

    @Override
    public void onError(String error, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(error, code);
        }
    }

    @Override
    public void onError(int error, int... code) {
        if (view != null) {
            view.hideProgress();
            view.showError(error, code);
        }
    }

    @Override
    public void onDetachView() {
        view = null;
    }
}
