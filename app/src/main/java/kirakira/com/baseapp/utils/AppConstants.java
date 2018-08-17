package kirakira.com.baseapp.utils;

public class AppConstants {
    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "mindorks_mvvm.db";

    public static final long NULL_INDEX = -1L;

    public static final String PREF_NAME = "mindorks_pref";

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";

    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String STATUS_CODE_FAILED = "failed";

    public static final String STATUS_CODE_SUCCESS = "success";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String MOVIE_API_KEY_TMDB = "0032af3df47158d76407feeae1199880";

    public static final String MOVIE_API_KEY_TMDB_2 = "?api_key=0032af3df47158d76407feeae1199880&language=en-US&page=1";

    public static final String MOVIE_URL_IMG_BASE = "https://image.tmdb.org/t/p/w500";

    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/";


    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
