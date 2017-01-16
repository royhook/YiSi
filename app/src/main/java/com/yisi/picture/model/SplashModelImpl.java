package com.yisi.picture.model;

import com.yisi.picture.model.inter.ISplashModel;
import com.yisi.picture.presenter.inter.ISplashPre;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashModelImpl implements ISplashModel {

    ISplashPre mSplashPresenter;

    public SplashModelImpl(ISplashPre splashPresenter){
        mSplashPresenter = splashPresenter;
    }


    @Override
    public void requestScreenPicture() {

    }
}
