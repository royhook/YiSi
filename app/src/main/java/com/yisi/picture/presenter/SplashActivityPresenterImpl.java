package com.yisi.picture.presenter;

import android.content.Intent;

import com.yisi.picture.MainActivity;
import com.yisi.picture.activity.SplashActivity;
import com.yisi.picture.activity.inter.ISplashActivity;
import com.yisi.picture.application.YiSiApplication;
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
