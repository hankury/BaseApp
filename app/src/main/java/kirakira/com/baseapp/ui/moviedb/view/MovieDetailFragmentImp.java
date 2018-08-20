package kirakira.com.baseapp.ui.moviedb.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;

import butterknife.BindView;
import kirakira.com.baseapp.R;
import kirakira.com.baseapp.ui.base.BaseFragment;
import kirakira.com.baseapp.ui.moviedb.model.MovieDetailResponse;
import kirakira.com.baseapp.utils.AppConstants;

public class MovieDetailFragmentImp extends BaseFragment {
    @BindView(R.id.txt_content)
    TextView txtContent;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_movie)
    ImageView imgMovie;
    @BindView(R.id.collaping_layout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.app_bar_image)
    ImageView imgBackground;
    private MovieDetailResponse movieDetailResponse;


    public static MovieDetailFragmentImp newInstance() {
        Bundle args = new Bundle();
        MovieDetailFragmentImp fragment = new MovieDetailFragmentImp();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        movieDetailResponse = (MovieDetailResponse) bundle.getSerializable(AppConstants.MOVIE_DETAIL_KEY);
        if (movieDetailResponse != null) {
            Glide.with(getActivity())
                    .load(AppConstants.MOVIE_URL_IMG_BASE + movieDetailResponse.getBackdropPath())
                    .into(imgBackground);

            Glide.with(getActivity())
                    .load(AppConstants.MOVIE_URL_IMG_BASE + movieDetailResponse.getPosterPath())
                    .into(imgMovie);

//            txtTitle.setText(movieDetailResponse.getTitle());
            txtContent.setText(movieDetailResponse.getOverview());
//            collapsingLayout.setTitle(movieDetailResponse.getTitle());
        }
    }
}
