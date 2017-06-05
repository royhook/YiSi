package com.yisi.picture.picturemodel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

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
    private RecyclerView mHeadRecyclerView;

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
            mHeadRecyclerView = new RecyclerView(getContext());
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
        mYiSiChildFragmentPre.requestAli();
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
    public RecyclerView getHeadView() {
        return mHeadRecyclerView;
    }

    @Override
    public View getTitleView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.view_ali_header, null, false);
    }

    @Override
    public View getDivideView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.view_divide, null, false);
    }

    @Override
    public View getLastView() {
        return  LayoutInflater.from(getContext()).inflate(R.layout.view_divide_last, null, false);
    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void onRefreshComlete() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onLoadingSuccess() {
        super.onLoadingSuccess();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadingFail() {
        super.onLoadingFail();
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mHeadRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        mHeadRecyclerView.setAdapter(adapter);
    }
}
