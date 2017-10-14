package com.yisi.picture.picturemodel.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.fragment.inter.IImageFragment;
import com.yisi.picture.picturemodel.presenter.ImagePresenter;

/**
 * Created by chenql on 2017/6/1.
 */

public class ImageFragment extends BaseFragment implements IImageFragment {
    int mId;
    RecyclerView mRecyclerView;
    private ImagePresenter mImagePresenter;


    public void setId(int id) {
        mId = id;
    }

    @Override
    protected void initViews() {
        mImagePresenter = new ImagePresenter(this);
        mRecyclerView = findview(R.id.rv_image);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_img;
    }

    @Override
    protected void initData() {
        request();
    }

    private void request() {
        mImagePresenter.request(true);
    }


    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getRequestId() {
        return mId;
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
