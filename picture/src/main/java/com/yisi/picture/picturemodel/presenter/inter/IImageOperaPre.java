package com.yisi.picture.picturemodel.presenter.inter;

import android.content.Intent;

import com.yisi.picture.baselib.base.inter.IBasePresenter;
import com.yisi.picture.picturemodel.bean.YiSiImage;

import java.util.List;

/**
 * Created by roy on 2017/2/11.
 */

public interface IImageOperaPre extends IBasePresenter {
    Intent getChildIntent();

    void getData();

    void onSuccess(List<YiSiImage> yiSiImages, int position);

    void onEmpty();

    void downloadImg();

    void setWallPaper();

    void setSystemWallPaper();

    void collectImg();
}
