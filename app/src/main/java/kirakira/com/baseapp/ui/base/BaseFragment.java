package kirakira.com.baseapp.ui.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
    private BaseActivity mActivity;

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
