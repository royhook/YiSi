package com.yisi.picture.picturemodel.presenter;

import com.yisi.picture.baselib.base.BasePresenterImpl;
import com.yisi.picture.picturemodel.bean.MainTab;
import com.yisi.picture.picturemodel.fragment.inter.IMainFragment;
import com.yisi.picture.picturemodel.model.MainFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IMainFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IMainFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainFragmentPreImpl extends BasePresenterImpl<IMainFragment, IMainFragmentModel> implements IMainFragmentPre {


    public MainFragmentPreImpl(IMainFragment baseView) {
        super(baseView);
    }

    @Override
    protected IMainFragmentModel setModel() {
        mModel = new MainFragmentModelImpl(this);
        return mModel;
    }


    @Override
    public void onEmpty() {
        mView.onEmpty();
    }

    @Override
    public void onFail() {
        mView.onLoadingFail();
    }


    @Override
    public void onSuccess(List<MainTab> tabs) {
        mView.onLoadingSuccess();
        mView.initMainPageChildPage(tabs);
    }

    @Override
    public void requestTab() {
        mModel.request();
    }
}
