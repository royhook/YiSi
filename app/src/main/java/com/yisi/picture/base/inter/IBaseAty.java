package com.yisi.picture.base.inter;

/**
 * Created by chenql on 2017/3/28.
 */

public interface IBaseAty extends IBaseView {

    void onLoadingPage();

    void onLoadingSuccess();

    void onLoadingFail();

    void onEmpty();
}
