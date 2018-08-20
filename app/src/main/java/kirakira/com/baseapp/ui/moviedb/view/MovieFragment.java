package kirakira.com.baseapp.ui.moviedb.view;

import java.util.List;

import kirakira.com.baseapp.ui.moviedb.model.Movie;
import kirakira.com.baseapp.ui.moviedb.model.MovieDetailResponse;

public interface MovieFragment {
    void listMovie(List<Movie> lstMovies);

    void openMovieDetail(Movie item);

    void showLoadings();

    void hideLoadings();

    void showMovieDetail(MovieDetailResponse movie);
}
