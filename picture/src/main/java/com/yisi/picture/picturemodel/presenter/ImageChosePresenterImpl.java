package com.yisi.picture.picturemodel.presenter;

import com.yisi.picture.baselib.base.BasePresenterImpl;
import com.yisi.picture.picturemodel.activity.inter.IImageChoseActivity;
import com.yisi.picture.picturemodel.model.ImageChoseModelImpl;
import com.yisi.picture.picturemodel.model.inter.IImageChoseModel;
import com.yisi.picture.picturemodel.presenter.inter.IImageChosePresenter;

/**
 * Created by chenql on 2017/10/12.
 */

public class ImageChosePresenterImpl extends BasePresenterImpl<IImageChoseActivity, IImageChoseModel> implements IImageChosePresenter {

    protected ImageChosePresenterImpl(IImageChoseActivity baseView) {
        super(baseView);
    }

    @Override
    protected IImageChoseModel setModel() {
        return new ImageChoseModelImpl(this);
    }
}
