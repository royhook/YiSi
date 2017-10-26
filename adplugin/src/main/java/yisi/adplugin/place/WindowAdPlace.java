package yisi.adplugin.place;

import android.view.View;
import android.view.WindowManager;

import yisi.adplugin.business.BaseAdPlace;
import yisi.adplugin.utils.WindowUtils;


/**
 * Created with Android Studio
 * </p>
 * Authour:xiaxf
 * </p>
 * Date:16/6/17.
 */

public abstract class WindowAdPlace extends BaseAdPlace {
    public WindowManager mWindowManager;
    public WindowManager.LayoutParams mLayoutParams;
    public View windowView;
    protected boolean isAddWindow;
    private boolean stop = true;
    public int mWindowType;

    public void removeWindow(boolean close) {
        if (windowView != null && mWindowManager != null) {
            try {
                mWindowManager.removeViewImmediate(windowView);
                isAddWindow = false;
            } catch (IllegalArgumentException e) {
            }

        }
        if (close) {
            windowView = null;
            mLayoutParams = null;
            stop = true;
        }
    }

    public void addWindow() {

        mWindowManager = WindowUtils.getWindowManager();

        if (!isAddWindow) {
            if (windowView != null && mLayoutParams != null) {
                try {
                    mWindowManager.addView(windowView, mLayoutParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isAddWindow = true;
            }
        }
        if (!stop)
            return;
        stop = false;
    }

    @Override
    public void onAdRequest() {
        super.onAdRequest();
    }

    @Override
    public void onAdClick() {
        super.onAdClick();
        removeWindow(true);
    }


    @Override
    public void onAdSkip() {
        removeWindow(true);
        super.onAdSkip();
    }

    @Override
    public void onAdFailed(String message) {
        removeWindow(true);
        super.onAdFailed(message);
    }

    @Override
    public void onAdPresent() {
        super.onAdPresent();
    }
}
