package com.yisi.picture.activity.inter;

import android.content.Context;
import android.content.Intent;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.baselib.base.inter.IBaseAty;

/**
 * Created by roy on 2017/2/5.
 */

public interface IAlbumAty extends IBaseAty {
    XRecyclerView getRecyclerView();

    Intent getAlbumIntent();

    Context getViewContext();

    void setToolBarTitle(String title);

    void dataRunOut();
}
