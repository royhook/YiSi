package com.yisi.picture.picturemodel.fragment.inter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yisi.picture.baselib.base.inter.IBaseAty;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlansFragment extends IBaseAty {

    RecyclerView getRecylerView();

    Context getViewContext();

    void dataOut();

    SwipeRefreshLayout getSwipeRefresh();

}
