package com.yisi.picture.presenter;

import com.yisi.picture.activity.inter.ISplashActivity;
import com.yisi.picture.presenter.inter.ISplashPresenter;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashActivityPresenterImpl implements ISplashPresenter {

    private ISplashActivity mSplashActivity;

    public void setmSplashActivity(ISplashActivity mSplashActivity) {
        this.mSplashActivity = mSplashActivity;
    }

}
