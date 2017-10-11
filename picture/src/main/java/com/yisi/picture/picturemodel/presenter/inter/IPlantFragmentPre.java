package com.yisi.picture.picturemodel.presenter.inter;


import com.yisi.picture.baselib.base.inter.IBaseRefreshPresenter;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlantFragmentPre<T> extends IBaseRefreshPresenter<T> {

    void bindBanner(List<RecommandPlantImage> images);

}
