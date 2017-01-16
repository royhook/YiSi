package com.yisi.picture.activity.inter;

import android.content.Context;
import android.content.Intent;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.base.inter.IBaseView;

/**
 * Created by roy on 2017/2/5.
 */

public interface IAlbumAty extends IBaseView {
    XRecyclerView getRecyclerView();

    Intent getAlbumIntent();

    Context getViewContext();
}
