package com.yisi.picture.base;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.adapter.base.BaseAdapter;
import com.yisi.picture.base.inter.IBaseModel;
import com.yisi.picture.base.inter.IBaseRefreshPresenter;
import com.yisi.picture.base.inter.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/2/5.
 */

public abstract class BaseRefreshPresenterImpl<V extends IBaseView, M extends IBaseModel, T> extends BasePresenterImpl<V, M> implements IBaseRefreshPresenter<T>, XRecyclerView.LoadingListener {
    protected int currentPage = 0;
    private static int REFRESH_TYPE = 0;
    private static final int REFRESH_REFRESH = 1;
    private static final int REFRESH_LOADMORE = 2;
    protected List<T> currentList;


    protected BaseRefreshPresenterImpl(V baseView) {
        super(baseView);
        currentPage = 0;
        if (currentList != null)
            currentList.clear();
        REFRESH_TYPE = 0;
    }

    public abstract void bindLayouManagerAndAdapter();

    public abstract BaseAdapter getRefreshAdapter();

    protected abstract XRecyclerView getRecyclerView();

    @Override
    public void onLoadMore() {
        REFRESH_TYPE = REFRESH_LOADMORE;
        currentPage++;
        request(false);
    }

    @Override
    public void onRefresh() {
        REFRESH_TYPE = REFRESH_REFRESH;
        currentPage = 0;
        request(false);
    }

    @Override
    public void onSuccess(List<T> t) {
        if (currentList == null) {
            currentList = new ArrayList<>();
            currentList.addAll(t);
        }
        bindLayouManagerAndAdapter();
        switch (REFRESH_TYPE) {
            case REFRESH_REFRESH:
                currentList.clear();
                currentList.addAll(t);
                getRefreshAdapter().notifyDataSetChanged();
                getRecyclerView().refreshComplete();
                break;

            case REFRESH_LOADMORE:
                currentList.addAll(t);
                if (currentList.size() - t.size() >= 0) {
                    getRefreshAdapter().notifyItemChanged(currentList.size() - t.size() + 1, currentList.size());
                    getRecyclerView().loadMoreComplete();
                }
                break;
        }
    }

    @Override
    public void onFail(int errorCode) {
        currentPage--;
    }
}
