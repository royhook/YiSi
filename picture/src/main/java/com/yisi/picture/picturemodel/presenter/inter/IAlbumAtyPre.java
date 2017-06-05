package com.yisi.picture.picturemodel.presenter.inter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.inter.IBaseRefreshPresenter;

/**
 * Created by roy on 2017/2/5.
 */

public interface IAlbumAtyPre<T> extends IBaseRefreshPresenter<T> {
    void initViewDatas();
    BaseQuickAdapter getAdapter();
}
