package com.yisi.picture.baselib.base;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by roy on 2017/2/5.
 */

public abstract class BaseRefreshActivity extends BaseActivity implements XRecyclerView.LoadingListener {
    protected int currentPage = 0;

    @Override
    public void onRefresh() {
        currentPage = 0;

    }

    @Override
    public void onLoadMore() {
        currentPage++;
    }

    public abstract void onRefreshData();

    public abstract void onLoadMoreData();
}
