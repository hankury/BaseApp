package kirakira.com.baseapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import kirakira.com.baseapp.ui.base.BaseActivity;
import kirakira.com.baseapp.ui.moviedb.view.MovieFragmentImp;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, MovieFragmentImp.newInstance())
                .commit();
    }
}
