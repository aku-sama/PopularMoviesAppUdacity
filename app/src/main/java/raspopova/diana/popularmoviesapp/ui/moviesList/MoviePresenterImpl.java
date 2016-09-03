package raspopova.diana.popularmoviesapp.ui.moviesList;

import java.util.ArrayList;
import java.util.List;

import raspopova.diana.popularmoviesapp.R;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieListObject;
import raspopova.diana.popularmoviesapp.reposytory.dataModel.movieObject;

/**
 * Created by Diana on 9/3/2016.
 */
public class MoviePresenterImpl implements MoviePresenter, MovieInteractor.onMovieGetListener {


    private MovieInteractor interactor;
    private MovieView view;

    private long currentPage = 1;
    private long endPage = 1;
    private List<movieObject> movieList;


    public MoviePresenterImpl() {
        interactor = new MovieInteractorImpl();
        movieList = new ArrayList<>();
    }

    @Override
    public void onAttacheView(MovieView view) {
        this.view = view;
    }

    @Override
    public void initialize() {
        if (view != null) {
            view.showProgress();
            interactor.getPopularMovie(currentPage, this);
        }
    }

    @Override
    public void getNewMoviePage() {
        if (view != null) {
            if (currentPage < endPage) {
                view.showProgress();
                interactor.getPopularMovie(currentPage + 1, this);
            } else {
                view.showError(R.string.no_more_pages);
            }
        }
    }

    @Override
    public void onMoviePreviewClick(int position) {
        if(view!=null){
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
