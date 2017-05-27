package com.yisi.picture.picturemodel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.fragment.inter.IMainPageChildFragment;
import com.yisi.picture.picturemodel.presenter.MainPageChildFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragment extends BaseFragment implements IMainPageChildFragment {
    private RecyclerView mRecyclerView;
    private IMainPageChildFragmentPre mYiSiChildFragmentPre;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mYiSiChildFragmentPre = new MainPageChildFragmentPreImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        if (mRecyclerView == null) {
            mRecyclerView = findview(R.id.hot_fragment_recycler);
            mSwipeRefreshLayout = findview(R.id.sr_fragment_hot);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        }
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        //防止重复请求数据
        mYiSiChildFragmentPre.request(true);
    }

    @Override
    public RecyclerView getRecycleView() {
        return mRecyclerView;
    }

    @Override
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeRefreshLayout.setOnRefreshListener(listener);
    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void onRefreshComlete() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onLoadingPage() {

    }

    @Override
    public void onLoadingSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadingFail() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }
}
