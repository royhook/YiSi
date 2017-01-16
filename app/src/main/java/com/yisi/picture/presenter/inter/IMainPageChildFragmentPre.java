package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBasePresenter;
import com.yisi.picture.bean.YiSiImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragmentPre extends IBasePresenter {
    void onSuccess(List<YiSiImage> yiSiImages);
    void onError(int errorCode);
    void request(int type_id,int page);
}
