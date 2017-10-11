package com.yisi.picture.picturemodel.presenter.inter;

import com.yisi.picture.baselib.base.inter.IBasePresenter;
import com.yisi.picture.picturemodel.bean.MainTab;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainFragmentPre extends IBasePresenter {

    void onSuccess(List<MainTab> tabs);

    void requestTab();


    void onEmpty();

    void onFail();

}
