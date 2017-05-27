package com.yisi.picture.picturemodel.presenter;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.MainPageChildImageAdapter;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.fragment.inter.IMainPageChildFragment;
import com.yisi.picture.picturemodel.model.MainPageChildFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentPreImpl extends BaseRefreshPresenterImpl<IMainPageChildFragment, IMainPageChildFragmentModel, YiSiImage> implements
        IMainPageChildFragmentPre, BaseQuickAdapter.OnItemClickListener {
    private MainPageChildImageAdapter mMainPageChildImageAdapter;

    public MainPageChildFragmentPreImpl(IMainPageChildFragment baseView) {
        super(baseView);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        mMainPageChildImageAdapter = new MainPageChildImageAdapter(currentList);
        mMainPageChildImageAdapter.setOnItemClickListener(this);
        mView.setOnRefreshListener(this);
        mView.getRecycleView().setAdapter(mMainPageChildImageAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mView.getRecycleView().getContext(), 3, 1, false);
        mView.getRecycleView().setLayoutManager(gridLayoutManager);
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return mMainPageChildImageAdapter;
    }

    @Override
    protected IMainPageChildFragmentModel setModel() {
        return new MainPageChildFragmentModelImpl(this);
    }


    @Override
    public void request(boolean readCache) {
        mModel.request(1, currentPage, readCache);
    }

    @Override
    public void onError(int errorCode) {
        LogUtils.d(errorCode + "");
        refreshPage();
    }

    @Override
    public void onEmpty() {

    }


    private void refreshPage() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Gson gson = new Gson();
        String json = gson.toJson(currentList);
        Intent intent = new Intent(mView.getRecycleView().getContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA, json);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, position);
        mView.getRecycleView().getContext().startActivity(intent);
    }
}
