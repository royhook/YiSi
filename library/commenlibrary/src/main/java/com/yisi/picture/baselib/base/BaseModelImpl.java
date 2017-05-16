package com.yisi.picture.baselib.base;

import com.yisi.picture.baselib.base.inter.IBaseModel;
import com.yisi.picture.baselib.base.inter.IBasePresenter;

/**
 * Created by roy on 2017/1/19.
 */

public abstract class BaseModelImpl<T extends IBasePresenter> implements IBaseModel {
    protected T mPresenter;

    protected BaseModelImpl(T basePresenter) {
        this.mPresenter = basePresenter;
    }
}
