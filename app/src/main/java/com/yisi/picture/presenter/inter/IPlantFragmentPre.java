package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBaseRefreshPresenter;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlantFragmentPre<T> extends IBaseRefreshPresenter<T> {
    void onError(int code);
}
