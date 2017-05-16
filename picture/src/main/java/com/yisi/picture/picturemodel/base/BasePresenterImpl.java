package com.yisi.picture.picturemodel.base;


import com.yisi.picture.picturemodel.base.inter.IBaseModel;
import com.yisi.picture.picturemodel.base.inter.IBasePresenter;
import com.yisi.picture.picturemodel.base.inter.IBaseView;

/**
 * Created by roy on 2017/1/19.
 */

public abstract class BasePresenterImpl<V extends IBaseView, M extends IBaseModel> implements IBasePresenter {
    protected V mView;
    protected M mModel;

    protected BasePresenterImpl(V baseView) {
        mView = baseView;
        mModel = setModel();
    }

    protected abstract M setModel();

}
