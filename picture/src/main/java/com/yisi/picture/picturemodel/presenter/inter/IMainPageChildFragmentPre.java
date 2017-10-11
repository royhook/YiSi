package com.yisi.picture.picturemodel.presenter.inter;


import com.yisi.picture.baselib.base.inter.IBaseRefreshPresenter;
import com.yisi.picture.picturemodel.bean.PlantModel;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragmentPre extends IBaseRefreshPresenter<PlantModel> {
    void onSuccess(List<PlantModel> yiSiImages);

    void onError(int errorCode);

    void onEmpty();
}
