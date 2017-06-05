package com.yisi.picture.baselib.base;

import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.baselib.base.inter.IBaseAty;
import com.yisi.picture.baselib.base.inter.IBaseModel;
import com.yisi.picture.baselib.base.inter.IBaseRefreshPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/2/5.
 */

public abstract class BaseRefreshPresenterImpl<V extends IBaseAty, M extends IBaseModel, T> extends BasePresenterImpl<V, M> implements IBaseRefreshPresenter<T>,
        XRecyclerView.LoadingListener, SwipeRefreshLayout.OnRefreshListener {
    protected int initPage = 0;
    protected int currentPage = 0;
    private static int REFRESH_NORMAL = 1;
    private static int REFRESH_TYPE = REFRESH_NORMAL;
    private static final int REFRESH_REFRESH = 2;
    private static final int REFRESH_LOADMORE = 3;
    protected List<T> currentList;

    public void setInitPage(int initPage) {
        this.initPage = initPage;
        currentPage = initPage;
    }

    protected BaseRefreshPresenterImpl(V baseView) {
        super(baseView);
        currentPage = initPage;
        if (currentList != null)
            currentList.clear();
        REFRESH_TYPE = 0;
    }

    public abstract void bindLayouManagerAndAdapter();

    public abstract BaseQuickAdapter getRefreshAdapter();


    @Override
    public void onLoadMore() {
        REFRESH_TYPE = REFRESH_LOADMORE;
        currentPage++;
        request(false);
    }

    @Override
    public void onRefresh() {
        REFRESH_TYPE = REFRESH_REFRESH;
        currentPage = initPage;
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
                mView.onRefreshComlete();
                REFRESH_TYPE = REFRESH_NORMAL;
                break;

            case REFRESH_LOADMORE:
                if (t.size() == 0) {
                    getRefreshAdapter().loadMoreEnd();
                    return;
                }
                currentList.addAll(t);
                if (currentList.size() - t.size() >= 0) {
                    getRefreshAdapter().notifyItemChanged(currentList.size() - t.size() + 1, currentList.size());
                    mView.onLoadMoreComplete();
                }
                REFRESH_TYPE = REFRESH_NORMAL;
                mView.onLoadMoreComplete();
                getRefreshAdapter().loadMoreComplete();
                break;
        }
        mView.onLoadingSuccess();
    }

    @Override
    public void onEmpty() {
        if (REFRESH_TYPE != REFRESH_LOADMORE) {
            mView.onEmpty();
        }else {
            getRefreshAdapter().loadMoreEnd();
        }
    }

    @Override
    public void onFail(int errorCode) {
        currentPage--;
        mView.onLoadingFail();
    }
}
