package com.yisi.picture.picturemodel.presenter.inter;


import com.yisi.picture.baselib.base.inter.IBaseRefreshPresenter;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragmentPre extends IBaseRefreshPresenter<YiSiImage> {
    void onSuccess(List<YiSiImage> yiSiImages);

    void onError(int errorCode);

    void onEmpty();
}
