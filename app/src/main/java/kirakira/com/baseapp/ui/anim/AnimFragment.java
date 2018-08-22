package kirakira.com.baseapp.ui.anim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import kirakira.com.baseapp.R;
import kirakira.com.baseapp.ui.base.BaseFragment;

public class AnimFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tab_view)
    LinearLayout tabView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    public AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.ll_tab_background)
    LinearLayout tabBackground;
    @BindView(R.id.ll_tab_foreground)
    LinearLayout tabForeground;
    @BindView(R.id.txt_view)
    TextView txtView;
    @BindView(R.id.img_view)
    ImageView imgView;

    private int appBarHeight = 0;
    private int tabViewHeight = 0;
    private int tabViewOffset = 0;
    private int tabTitleOffset = 0;
    private DisplayMetrics displayMetrics;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_anim;
    }

    public static AnimFragment newInstance() {
        Bundle args = new Bundle();
        AnimFragment fragment = new AnimFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        displayMetrics = getActivity().getResources().getDisplayMetrics();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, final int verticalOffset) {
                if (appBarHeight == 0) {
                    appBarLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            if (appBarHeight == 0) {
                                appBarHeight = appBarLayout.getHeight();
                                tabViewHeight = tabView.getHeight();
                                tabViewOffset = (tabBackground.getHeight() - tabForeground.getHeight()) / 2
                                ;
                                tabTitleOffset = (int) ((tabBackground.getHeight() - tabForeground.getHeight()) / 2
                                        + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                        10, displayMetrics));
                                processProgress(verticalOffset);
                            }
                        }
                    });
                } else {
                    processProgress(verticalOffset);
                }
            }
        });
        imgView.setOnClickListener(this);
        txtView.setOnClickListener(this);
    }

    private void processProgress(int verticalOffset) {
        float progress = (float) -verticalOffset / appBarLayout.getTotalScrollRange();
        Log.e("Ranger", progress + "");
        Log.e("verticalOffset", verticalOffset + "");

        if (callback != null) {
            callback.onTransform(progress);
        }

        tabView.setX(16 + (-verticalOffset) * progress / 3);
        tabView.setY(appBarHeight - tabViewHeight + verticalOffset + progress * tabViewOffset);

        if (progress == 1f) {
            imgView.setImageResource(R.drawable.y1);
            txtView.setText("12345");
        } else {
            imgView.setImageResource(R.drawable.ic_launcher_background);
            txtView.setText(getActivity().getResources().getString(R.string.app_google_play_store_link));
        }

    }

    public void setCallback(TransformCallback callback) {
        this.callback = callback;
    }

    private TransformCallback callback;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_view:
                Toast.makeText(getActivity(), "Img Clicked !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_view:
                Toast.makeText(getActivity(), "Text Clicked !", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public interface TransformCallback {
        void onTransform(float progress);
    }
}
