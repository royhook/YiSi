package com.yisi.picture.presenter;

import com.yisi.picture.activity.inter.IMainAty;
import com.yisi.picture.baselib.base.BasePresenterImpl;
import com.yisi.picture.model.MainAtyModel;
import com.yisi.picture.model.inter.IMainAtyModel;
import com.yisi.picture.presenter.inter.IMainAtyPre;

/**
 * Created by roy on 2017/1/14.
 */

public class MainAtyPreImpl extends BasePresenterImpl<IMainAty, IMainAtyModel> implements IMainAtyPre {

    public MainAtyPreImpl(IMainAty baseView) {
        super(baseView);
    }


    @Override
    public void initDatas() {
        mModel.request();
    }

    @Override
    protected IMainAtyModel setModel() {
        return new MainAtyModel(this);
    }
}
