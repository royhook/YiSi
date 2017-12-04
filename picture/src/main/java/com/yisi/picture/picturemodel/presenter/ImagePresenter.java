package com.yisi.picture.picturemodel.presenter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.ImageAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.fragment.inter.IImageFragment;
import com.yisi.picture.picturemodel.model.ImageModel;
import com.yisi.picture.picturemodel.model.inter.IImageModel;
import com.yisi.picture.picturemodel.presenter.inter.IImagePresenter;

import java.util.ArrayList;
import java.util.List;

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
        mView.bindLayoutManager(manager);
        mView.bindAdapter(mImageAdapter);
        mView.setSwipeLayoutListenr(this);
        mImageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        });
        mImageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<Image> imgs = new ArrayList<>();
                for (int i = 0; i < currentList.size(); i++) {
                    Image eImage = new Image();
                    eImage.setUrl(currentList.get(i).getUrl());
                    imgs.add(eImage);
                }

                ActivityOptions transitionActivityOptions = null;
                String json = new Gson().toJson(imgs);
                ImageView imageView = ViewUtils.findView(view, R.id.adapter_plant_img);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) mView.getViewContext(),
                            imageView,
                            YiSiApplication.getStringResource(R.string.trs_plant_operate));
                    mView.getViewContext().startActivity(ImageOperateActivity.getDataIntent(position, json), transitionActivityOptions.toBundle());
                } else {
                    mView.getViewContext().startActivity(ImageOperateActivity.getDataIntent(position, json));
                }
            }
        });
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return mImageAdapter;
    }
}
