package com.yisi.picture.picturemodel.fragment;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.presenter.PlantFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

/**
 * Created by roy on 2017/1/14.
 */

public class PlantFragment extends BaseFragment implements IPlansFragment {

    RecyclerView mRecyclerView;
    IPlantFragmentPre plantFragmentPre;
    private AppBarLayout mAppBarLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void initViews() {
        mRecyclerView = findview(R.id.plans_fragment_recycler);
        plantFragmentPre = new PlantFragmentPreImpl(this);
        mAppBarLayout = findview(R.id.main_appbar);
        mSwipeRefreshLayout = findview(R.id.sr_fragment_plans);
        mSwipeRefreshLayout.setProgressViewOffset(true, -20, 100);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
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
    public RecyclerView getRecylerView() {
        return mRecyclerView;
    }

    @Override
    public Context getViewContext() {
        return this.getContext();
    }

    @Override
    public void dataOut() {
    }

    @Override
    public SwipeRefreshLayout getSwipeRefresh() {
        return mSwipeRefreshLayout;
    }

    @Override
    public void onRefreshComlete() {
        super.onRefreshComlete();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
