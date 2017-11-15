package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.picturemodel.activity.ImageChoseActivity;
import com.yisi.picture.picturemodel.adapter.PlantAdapter;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.model.PlantFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IPlantModel;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

import java.util.List;

import yisi.adplugin.utils.CoinUtils;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantFragmentPreImpl extends BaseRefreshPresenterImpl<IPlansFragment, IPlantModel, RecommandPlantImage> implements IPlantFragmentPre<RecommandPlantImage>,
        BaseQuickAdapter.OnItemClickListener {
    PlantAdapter mPlantAdapter;

    public PlantFragmentPreImpl(IPlansFragment baseView) {
        super(baseView);
    }

    @Override
    protected void initAdapter() {
        mPlantAdapter = new PlantAdapter(currentList);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        mView.getRecylerView().setLayoutManager(new GridLayoutManager(mView.getViewContext(), 2, 1, false));
        mView.getRecylerView().setAdapter(mPlantAdapter);
        mView.getSwipeRefresh().setOnRefreshListener(this);
        mPlantAdapter.setOnItemClickListener(this);
        mPlantAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        });
        mPlantAdapter.addHeaderView(mView.getBanerView());
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return mPlantAdapter;
    }


    @Override
    protected PlantFragmentModelImpl setModel() {
        return new PlantFragmentModelImpl(this);
    }


    @Override
    public void request(boolean readCache) {
        mModel.request(currentPage, readCache);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        RecommandPlantImage image = currentList.get(position);
        if (CoinUtils.canBuy(image.getId(), image.getCoin(), view)) {
            startImageChoseActivity(view, image);
        }

    }

    @Override
    public void bindBanner(List<RecommandPlantImage> images) {
        mView.bindBanner(images);
    }


    public void startImageChoseActivity(View view, RecommandPlantImage image) {
        String json = new Gson().toJson(image.getImage_list());
        Intent intent = ImageChoseActivity.getDateIntent(json, image.getId(), image.getName(), image.getImage_url());
        mView.getViewContext().startActivity(intent);
    }

}
