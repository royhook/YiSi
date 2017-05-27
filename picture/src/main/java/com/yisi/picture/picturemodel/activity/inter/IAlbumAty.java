package com.yisi.picture.picturemodel.activity.inter;

import android.content.Context;
import android.content.Intent;

import com.yisi.picture.baselib.base.inter.IBaseRefreshAty;

/**
 * Created by roy on 2017/2/5.
 */

public interface IAlbumAty extends IBaseRefreshAty {

    Intent getAlbumIntent();

    Context getViewContext();

    void setToolBarTitle(String title);

    void dataRunOut();

}
