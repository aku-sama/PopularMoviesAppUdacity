package raspopova.diana.popularmoviesapp.ui.moviesList;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.app.MovieApplication;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;

/**
 * Created by Diana on 9/3/2016.
 */
public class MoviePresenterImpl implements MoviePresenter, MovieInteractor.onMovieGetListener {


    private MovieInteractor interactor;
    private MovieView view;

    private long currentPage = 1;
    private long endPage = 2;
    private List<movieObject> movieList;
    private String currentOrdering = "0";


    public MoviePresenterImpl() {
        interactor = new MovieInteractorImpl();
        movieList = new ArrayList<>();
        SharedPreferences pref = PreferenceManager
                .getDefaultSharedPreferences(MovieApplication.getInstance());
        currentOrdering = pref.getString(MovieApplication.getInstance().getString(R.string.pref_sort_key),
                MovieApplication.getInstance().getString(R.string.pref_default_value));
    }

    @Override
    public void onAttacheView(MovieView view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        if (view != null) {
            view.showProgress();
            //check ordering change
            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(MovieApplication.getInstance());
            String sortOrder = pref.getString(MovieApplication.getInstance().getString(R.string.pref_sort_key),
                    MovieApplication.getInstance().getString(R.string.pref_default_value));

            //if order was changed, reset list
            if (!currentOrdering.equals(sortOrder)) {
                movieList.clear();
                currentPage = 1;
                endPage = 2;
                currentOrdering = sortOrder;
            }

            if (currentOrdering.equals("0"))
                interactor.getPopularMovie(currentPage, this);
            else
                interactor.getTopRatedMovie(currentPage, this);
        }
    }

    @Override
    public void getNewMoviePage() {
        if (view != null) {
            if (currentPage < endPage) {
                view.showProgress();

                if (currentOrdering.equals("0"))
                    interactor.getPopularMovie(currentPage + 1, this);
                else
                    interactor.getTopRatedMovie(currentPage + 1, this);

            } else {
                view.showError(R.string.no_more_pages);
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
    public void onSuccess(movieListObject result) {
        if (view != null) {
            view.hideProgress();
            movieList.addAll(result.getResults());
            currentPage = result.getPage();
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
