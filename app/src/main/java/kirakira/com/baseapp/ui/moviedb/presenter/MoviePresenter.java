package kirakira.com.baseapp.ui.moviedb.presenter;

public interface MoviePresenter {
    void getMovie(String apiKey, int page);

    void getMovieDetail(int movieId, String apiKey);
}
