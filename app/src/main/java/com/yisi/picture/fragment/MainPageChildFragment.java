package com.yisi.picture.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.R;
import com.yisi.picture.activity.ImageOperateActivity;
import com.yisi.picture.adapter.MainPageChildImageAdapter;
import com.yisi.picture.adapter.inter.OnItemClickListener;
import com.yisi.picture.base.BaseRefreshFragment;
import com.yisi.picture.bean.YiSiImage;
import com.yisi.picture.fragment.inter.IMainPageChildFragment;
import com.yisi.picture.presenter.MainPageChildFragmentPreImpl;
import com.yisi.picture.presenter.inter.IMainPageChildFragmentPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragment extends BaseRefreshFragment implements IMainPageChildFragment, OnItemClickListener {
    private int currentPage;
    private int type_id;
    private boolean mLoadType;// true refresh  false loadmore
    private XRecyclerView mRecyclerView;
    private IMainPageChildFragmentPre mYiSiChildFragmentPre;
    private MainPageChildImageAdapter mMainPageChildImageAdapter;
    private List<YiSiImage> mYiSiImages;


    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mYiSiChildFragmentPre = new MainPageChildFragmentPreImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        mRecyclerView = findview(R.id.hot_fragment_recycler);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        //防止重复请求数据
        if (mYiSiImages == null) {
            mYiSiImages = new ArrayList<>();
        }
        if (mYiSiImages.size() == 0) {
            mYiSiChildFragmentPre.request(type_id, currentPage);
            mMainPageChildImageAdapter = new MainPageChildImageAdapter(mYiSiImages);
            mMainPageChildImageAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mMainPageChildImageAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, 1, false);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }
    }


    @Override
    public void bindRecylerViewLoadMore(final List<YiSiImage> yiSiImages) {
        if (mYiSiImages.size() - yiSiImages.size() >= 0) {
            mMainPageChildImageAdapter.notifyItemChanged(mYiSiImages.size() - yiSiImages.size() + 1, mYiSiImages.size());
        }

        mRecyclerView.loadMoreComplete();
    }

    @Override
    public void bindRecylerViewRefresh(List<YiSiImage> yiSiImages) {
        currentPage++;
        mMainPageChildImageAdapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
    }

    @Override
    public void onDataRunOut() {
        mRecyclerView.setNoMore(true);
    }

    @Override
    public void onNoLastestData() {
        mRecyclerView.refreshComplete();
    }

    @Override
    public List<YiSiImage> getYiSiImages() {
        return mYiSiImages;
    }

    @Override
    public boolean isLoadMoreOrRefresh() {
        return mLoadType;
    }

    @Override
    public void onRefreshData() {
        mLoadType = true;
        currentPage = 0;
        mYiSiChildFragmentPre.request(type_id, currentPage);

    }

    @Override
    public void onLoadMoreData() {
        currentPage++;
        mLoadType = false;
        mYiSiChildFragmentPre.request(type_id, currentPage);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getContext(), ImageOperateActivity.class);
        intent.putExtra("imgUrl", mYiSiImages.get(position - 1).getImg_url());
        startActivity(intent);
    }
}
