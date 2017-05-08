package com.yisi.picture.fragment;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.R;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.fragment.inter.IPlansFragment;
import com.yisi.picture.presenter.PlantFragmentPreImpl;
import com.yisi.picture.presenter.inter.IPlantFragmentPre;

/**
 * Created by roy on 2017/1/14.
 */

public class PlantFragment extends BaseFragment implements IPlansFragment {

    XRecyclerView mXRecyclerView;
    IPlantFragmentPre plantFragmentPre;
    ContentLoadingProgressBar mContentLoadingProgressBar;

    @Override
    protected void initViews() {
        mXRecyclerView = findview(R.id.plans_fragment_recycler);
        mContentLoadingProgressBar = findview(R.id.fragment_process);
        plantFragmentPre = new PlantFragmentPreImpl(this);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_plans;
    }

    @Override
    protected void initData() {
        plantFragmentPre.request(true);
    }

    @Override
    public XRecyclerView getRecylerView() {
        return mXRecyclerView;
    }

    @Override
    public Context getViewContext() {
        return this.getContext();
    }

    @Override
    public void dataOut() {
        mXRecyclerView.setNoMore(true);
    }

    @Override
    public void onLoadingPage() {
        mContentLoadingProgressBar.setVisibility(View.VISIBLE);
        mXRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingSuccess() {
        mContentLoadingProgressBar.setVisibility(View.GONE);
        mXRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingFail() {

    }
}
