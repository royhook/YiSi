package com.yisi.picture.model;

import com.yisi.picture.model.inter.ISplashModel;
import com.yisi.picture.presenter.inter.ISplashPresenter;

/**
 * Created by roy on 2017/1/13.
 */

public class SplashModelImpl implements ISplashModel {

    ISplashPresenter mSplashPresenter;

    public SplashModelImpl(ISplashPresenter splashPresenter){
        mSplashPresenter = splashPresenter;
    }


    @Override
    public void requestScreenPicture() {

    }
}
