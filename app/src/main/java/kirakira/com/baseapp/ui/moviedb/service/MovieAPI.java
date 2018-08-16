package kirakira.com.baseapp.ui.moviedb.service;

import kirakira.com.baseapp.ui.moviedb.model.MovieDetailResponse;
import kirakira.com.baseapp.ui.moviedb.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPI {
    @GET("")
    Call<MovieResponse> getMovie();

    @GET("/movie/")
    Call<MovieDetailResponse> getMovieDetail(String idMovie);
}
