package kirakira.com.baseapp.ui.moviedb.presenter;

import android.view.View;

import kirakira.com.baseapp.ui.moviedb.service.MovieService;
import kirakira.com.baseapp.ui.moviedb.view.MovieFragment;

public class MoviePresenterImp implements MoviePresenter {
    private MovieFragment movieFragment;
    private MovieService movieService;


    public MoviePresenterImp(MovieFragment view) {
        this.movieFragment = view;

        if (this.movieService == null) {
            movieService = new MovieService();
        }

    }

    @Override
    public void getMovie() {

    }

    @Override
    public void getMovieDetail(String idMovie) {

    }
}
