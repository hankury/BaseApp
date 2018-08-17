package kirakira.com.baseapp.ui.moviedb.model;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kirakira.com.baseapp.R;
import kirakira.com.baseapp.ui.custom.RoundedImageView;
import kirakira.com.baseapp.utils.AppConstants;

public class Movie extends AbstractItem<Movie, Movie.ViewHolder> {
    private String idMovie;
    private String titleMovie;
    private String posterPath;
    private String backdropPath;
    private String overView;
    private String releaseDate;

    public Movie() {
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    public Movie(String idMovie, String titleMovie, String posterPath, String backdropPath, String overView, String releaseDate) {
        this.idMovie = idMovie;
        this.titleMovie = titleMovie;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overView = overView;
        this.releaseDate = releaseDate;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int getType() {
        return Integer.parseInt(idMovie);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_movie;
    }

    public static class ViewHolder extends FastAdapter.ViewHolder<Movie> {
        @BindView(R.id.img_movie)
        RoundedImageView img;
        @BindView(R.id.txt_title_movie)
        TextView txtTitle;
        @BindView(R.id.txt_release_date)
        TextView txtReleaseDate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(Movie item, List<Object> payloads) {
            if (item.getPosterPath() != null) {
                Glide.with(itemView.getContext())
                        .load(AppConstants.MOVIE_URL_IMG_BASE + item.getPosterPath())
                        .into(img);
            }
            if (!StringUtils.isEmpty(item.getTitleMovie())) {
                txtTitle.setText(item.getTitleMovie());
            }
            if (!StringUtils.isEmpty(item.getReleaseDate())) {
                txtReleaseDate.setText(item.getReleaseDate());
            }
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mCallBack.onClick(position);
//                }
//            });
        }

        @Override
        public void unbindView(Movie item) {
            img.setImageDrawable(null);
            txtTitle.setText("");
            txtReleaseDate.setText("");
        }

    }
}
