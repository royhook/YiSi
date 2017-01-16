package com.yisi.picture.fragment;

import android.support.v7.widget.RecyclerView;

import com.yisi.picture.R;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.bean.HotImage;
import com.yisi.picture.fragment.inter.IHotFragment;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class HotFragment extends BaseFragment implements IHotFragment{
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        mRecyclerView = findview(R.id.hot_fragment_recycler);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void bindRecylerViewData(List<HotImage> hotImages) {
        
    }
}
