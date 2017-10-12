package com.yisi.picture.picturemodel.model;

import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.model.inter.IImageChoseModel;
import com.yisi.picture.picturemodel.presenter.inter.IImageChosePresenter;

/**
 * Created by chenql on 2017/10/12.
 */

public class ImageChoseModelImpl extends BaseModelImpl<IImageChosePresenter> implements IImageChoseModel {

    public ImageChoseModelImpl(IImageChosePresenter basePresenter) {
        super(basePresenter);
    }
}
