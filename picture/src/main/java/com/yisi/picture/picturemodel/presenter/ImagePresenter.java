package com.yisi.picture.picturemodel.presenter;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.picturemodel.adapter.ImageAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.fragment.inter.IImageFragment;
import com.yisi.picture.picturemodel.model.ImageModel;
import com.yisi.picture.picturemodel.model.inter.IImageModel;
import com.yisi.picture.picturemodel.presenter.inter.IImagePresenter;

/**
 * Created by chenql on 2017/10/14.
 */

public class ImagePresenter extends BaseRefreshPresenterImpl<IImageFragment, IImageModel, Image> implements IImagePresenter {

    ImageAdapter mImageAdapter;

    public ImagePresenter(IImageFragment baseView) {
        super(baseView);
    }

    @Override
    public void request(boolean readCache) {
        mModel.request(mView.getRequestId(), currentPage);
    }

    @Override
    protected IImageModel setModel() {
        return new ImageModel(this);
    }

    @Override
    protected void initAdapter() {
        mImageAdapter = new ImageAdapter(currentList);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        GridLayoutManager manager = new GridLayoutManager(mView.getViewContext(), 2, 1, false);
        mView.bindAdapter(mImageAdapter);
        mView.bindLayoutManager(manager);
        mImageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        });
        mImageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return mImageAdapter;
    }
}
