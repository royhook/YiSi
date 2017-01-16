package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBasePresenter;
import com.yisi.picture.bean.MainPage;
import com.yisi.picture.bean.MainSliderBanner;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainFragmentPre extends IBasePresenter{

    void onSliderBannerSuccess(List<MainSliderBanner> mainSliderBanners);

    void onContentSuccess(List<MainPage> mainContents);

    void requestBannerAndShow();

    void requestContentAndShow();

}
