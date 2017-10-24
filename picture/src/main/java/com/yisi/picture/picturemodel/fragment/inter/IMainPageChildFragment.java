package com.yisi.picture.picturemodel.fragment.inter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yisi.picture.baselib.base.inter.IBaseRefreshAty;


/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragment extends IBaseRefreshAty {


    RecyclerView getRecycleView();

    void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener);

    RecyclerView getHeadView();

    View getTitleView();

    View getDivideView();

    View getLastView();

    Activity getBaseActivity();

}
