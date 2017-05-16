package com.yisi.picture.picturemodel.presenter.inter;


import com.yisi.picture.picturemodel.base.inter.IBasePresenter;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragmentPre extends IBasePresenter {
    void onSuccess(List<YiSiImage> yiSiImages);

    void onError(int errorCode);

    void onEmpty();

    void request(int type_id, int page, boolean readcache);
}
