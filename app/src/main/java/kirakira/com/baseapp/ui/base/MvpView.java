package kirakira.com.baseapp.ui.base;

import android.support.annotation.StringRes;

public interface MvpView {
    void showMessage(String message);

    void showMessage(@StringRes int resId);
}
