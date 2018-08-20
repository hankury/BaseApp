package kirakira.com.baseapp.ui.moviedb.service;

import kirakira.com.baseapp.ui.moviedb.model.MovieDetailResponse;
import kirakira.com.baseapp.ui.moviedb.model.MovieResponse;
import kirakira.com.baseapp.utils.AppConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("movie/popular")
    Call<MovieResponse> getMovie(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetail(@Path("movie_id") int idMovie, @Query("api_key") String apiKey);
}
