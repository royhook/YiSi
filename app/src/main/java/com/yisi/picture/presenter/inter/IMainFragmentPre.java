package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBasePresenter;
import com.yisi.picture.bean.MainPage;
import com.yisi.picture.bean.PlantBrowse;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainFragmentPre extends IBasePresenter {

    void onSliderBannerSuccess(List<PlantBrowse> mainSliderBanners);

    void onContentSuccess(List<MainPage> mainContents);

    void requestBannerAndShow();

    void requestContentAndShow();

    void onRecoverState(int currentFragment);

}
