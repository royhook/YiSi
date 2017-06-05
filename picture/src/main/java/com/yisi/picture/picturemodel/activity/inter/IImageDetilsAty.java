package com.yisi.picture.picturemodel.activity.inter;

import android.content.Context;

import com.yisi.picture.baselib.base.inter.IBaseRefreshAty;

/**
 * Created by chenql on 2017/6/1.
 */

public interface IImageDetilsAty extends IBaseRefreshAty {
    void setHeadImageUrl(String imageUrl);

    Context getViewContext();

    int getId();
}
