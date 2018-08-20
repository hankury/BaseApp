package kirakira.com.baseapp.ui.moviedb.presenter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kirakira.com.baseapp.ui.moviedb.model.Movie;
import kirakira.com.baseapp.ui.moviedb.model.MovieDetailResponse;
import kirakira.com.baseapp.ui.moviedb.model.MovieResponse;
import kirakira.com.baseapp.ui.moviedb.model.MovieResult;
import kirakira.com.baseapp.ui.moviedb.service.MovieService;
import kirakira.com.baseapp.ui.moviedb.view.MovieFragment;
import kirakira.com.baseapp.utils.AppConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenterImp implements MoviePresenter {
    private MovieFragment movieFragment;
    private MovieService movieService;
    private List<Movie> lstMovie = new ArrayList<>();

    public MoviePresenterImp(MovieFragment view) {
        this.movieFragment = view;

        if (this.movieService == null) {
            movieService = new MovieService();
        }

    }

    @Override
    public void getMovie(String apiKey, int page) {
//        movieFragment.showLoadings();
        movieService.getAPI()
                .getMovie(apiKey, page)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        MovieResponse movieResponse = response.body();
                        if (movieResponse != null) {
//                            movieFragment.hideLoadings();
                            List<MovieResult> results = movieResponse.getResults();
                            if (results != null) {
                                lstMovie.clear();
                                for (MovieResult model :
                                        results) {
                                    lstMovie.add(new Movie(
                                            model.getId() + "",
                                            model.getOriginalTitle(),
                                            model.getPosterPath(),
                                            model.getBackdropPath(),
                                            model.getOverview(),
                                            model.getReleaseDate()
                                    ));
                                }
                                movieFragment.listMovie(lstMovie);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getMovieDetail(int movieId, String apiKey) {
        movieFragment.showLoadings();
        movieService.getAPI()
                .getMovieDetail(movieId, apiKey)
                .enqueue(new Callback<MovieDetailResponse>() {
                    @Override
                    public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                        MovieDetailResponse detailResponse = response.body();
                        if (detailResponse != null) {
                            movieFragment.hideLoadings();
                            movieFragment.showMovieDetail(detailResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                        movieFragment.hideLoadings();
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
