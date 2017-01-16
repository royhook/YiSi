package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBasePresenter;
import com.yisi.picture.bean.HotImage;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IHotFragmentPre extends IBasePresenter {
    void onSuccess(List<HotImage> hotImages);
    void onError(int errorCode);
}
