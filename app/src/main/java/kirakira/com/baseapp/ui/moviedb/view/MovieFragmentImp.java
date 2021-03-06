package kirakira.com.baseapp.ui.moviedb.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;

import java.util.List;

import butterknife.BindView;
import kirakira.com.baseapp.R;
import kirakira.com.baseapp.ui.base.BaseFragment;
import kirakira.com.baseapp.ui.moviedb.model.Movie;
import kirakira.com.baseapp.ui.moviedb.model.MovieDetailResponse;
import kirakira.com.baseapp.ui.moviedb.presenter.MoviePresenterImp;
import kirakira.com.baseapp.utils.AppConstants;


public class MovieFragmentImp extends BaseFragment implements MovieFragment {
    @BindView(R.id.rcv_movie)
    RecyclerView recyclerView;
    @BindView(R.id.edt_search)
    EditText edtSearch;
    private ItemAdapter<Movie> itemAdapter;
    private ItemAdapter<ProgressItem> footer;
    private FastAdapter fastAdapter;
    private MoviePresenterImp moviePresenterImp;
    private EndlessRecyclerOnScrollListener onScrollListener;

    public static MovieFragmentImp newInstance() {
        Bundle args = new Bundle();
        MovieFragmentImp fragment = new MovieFragmentImp();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void listMovie(List<Movie> lstMovies) {
        if (lstMovies != null) {
            itemAdapter.add(lstMovies);
        }
    }

    @Override
    public void openMovieDetail(Movie item) {

        moviePresenterImp.getMovieDetail(Integer.parseInt(item.getIdMovie()), AppConstants.MOVIE_API_KEY_TMDB);
//        Toast.makeText(getActivity(), item.getTitleMovie() + "\n" + item.getOverView()
//                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void hideLoadings() {
        hideLoading();
    }

    @Override
    public void showMovieDetail(MovieDetailResponse movie) {

        openDetaiFragment(movie);
        if (movie != null) {
            Log.e("Detai", movie.getOverview() + "\n" + movie.getTitle());
        }
    }

    private void openDetaiFragment(MovieDetailResponse movie) {
        MovieDetailFragmentImp fragmentImp = new MovieDetailFragmentImp();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.MOVIE_DETAIL_KEY, movie);
        fragmentImp.setArguments(bundle);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, fragmentImp)
                .addToBackStack(null)
                .commit();


    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        //create the ItemAdapter holding your Items
        itemAdapter = new ItemAdapter();
        footer = new ItemAdapter<>();
        fastAdapter = FastAdapter.with(footer);
        fastAdapter.addAdapter(0, itemAdapter);
//        fastAdapter.addAdapter(1, itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fastAdapter);

        moviePresenterImp = new MoviePresenterImp(this);
        moviePresenterImp.getMovie(AppConstants.MOVIE_API_KEY_TMDB, 1);

        recycleClick();
        filter();
        loadMore();

        fastAdapter.saveInstanceState(savedInstanceState);
    }

    private void loadMore() {
        onScrollListener = new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                footer.clear();
                footer.add(new ProgressItem().withEnabled(false));
                moviePresenterImp.getMovie(AppConstants.MOVIE_API_KEY_TMDB, currentPage + 1);

            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
    }

    private void filter() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemAdapter.filter(edtSearch.getText().toString().toLowerCase());
                if (edtSearch.getText().length() != 0) {
                    footer.clear();
                } else {
                    footer.add(new ProgressItem().withEnabled(false));
                }

            }
        });

        itemAdapter.getItemFilter().withFilterPredicate(new IItemAdapter.Predicate<Movie>() {
            @Override
            public boolean filter(Movie item, CharSequence constraint) {
                return item.getTitleMovie().toLowerCase().contains(edtSearch.getText().toString());
            }
        });
    }

    private void recycleClick() {
        fastAdapter.withEventHook(new ClickEventHook<Movie>() {
            @Override
            public void onClick(View v, int position, FastAdapter<Movie> fastAdapter, Movie item) {
                openMovieDetail(item);
            }

            @Nullable
            @Override
            public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
                //return the views on which you want to bind this event
                if (viewHolder instanceof Movie.ViewHolder) {
                    return ((Movie.ViewHolder) viewHolder).itemView;
                }
                return null;
            }

        });

    }


}
