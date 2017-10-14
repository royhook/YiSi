package com.yisi.picture.picturemodel.fragment.inter;

import android.content.Context;

import com.yisi.picture.baselib.base.inter.IBaseRefreshAty;

/**
 * Created by chenql on 2017/10/14.
 */

public interface IImageFragment extends IBaseRefreshAty {

    int getRequestId();

    Context getViewContext();
}
