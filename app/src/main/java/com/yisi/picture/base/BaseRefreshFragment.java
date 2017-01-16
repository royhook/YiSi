package com.yisi.picture.base;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by roy on 2017/1/22.
 */

public abstract class BaseRefreshFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    @Override
    public void onRefresh() {
        onRefreshData();
    }

    @Override
    public void onLoadMore() {
        onLoadMoreData();
    }

    public abstract void onRefreshData();

    public abstract void onLoadMoreData();

}
