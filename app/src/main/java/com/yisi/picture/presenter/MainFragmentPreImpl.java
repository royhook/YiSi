package com.yisi.picture.presenter;

import com.yisi.picture.base.BasePresenterImpl;
import com.yisi.picture.bean.MainContent;
import com.yisi.picture.bean.MainSliderBanner;
import com.yisi.picture.fragment.inter.IMainFragment;
import com.yisi.picture.model.MainFragmentModelImpl;
import com.yisi.picture.model.inter.IMainFragmentModel;
import com.yisi.picture.presenter.inter.IMainFragmentPre;

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
    public void requestBannerAndShow() {
        mModel.requestBannerData();
    }

    @Override
    public void requestContentAndShow() {
        mModel.requestContentData();
    }

    @Override
    public void onSliderBannerSuccess(List<MainSliderBanner> mainSliderBanners) {
        mView.initSliderBannerData(mainSliderBanners);
    }

    @Override
    public void onContentSuccess(List<MainContent> mainContents) {
        mView.initTitle(mainContents);
    }
}
