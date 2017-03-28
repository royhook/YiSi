package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBaseRefreshPresenter;

/**
 * Created by roy on 2017/2/5.
 */

public interface IAlbumAtyPre<T> extends IBaseRefreshPresenter<T> {
    void initViewDatas();

    void onFail(int errorCode);
}
