package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.picturemodel.activity.ImageChoseActivity;
import com.yisi.picture.picturemodel.activity.inter.IImageDetilsAty;
import com.yisi.picture.picturemodel.adapter.AliPlantAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.bean.PlantImage;
import com.yisi.picture.picturemodel.model.ImageDetilsModelImpl;
import com.yisi.picture.picturemodel.model.inter.IImageDetilsModel;
import com.yisi.picture.picturemodel.presenter.inter.IDetilsPre;

import java.util.List;

/**
 * Created by chenql on 2017/6/1.
 */

public class ImageDetilsPreImpl extends BaseRefreshPresenterImpl<IImageDetilsAty, IImageDetilsModel, PlantImage> implements IDetilsPre {

    AliPlantAdapter mAliPlantAdapter;
    List<PlantImage> mAliImages;

    public ImageDetilsPreImpl(IImageDetilsAty baseView) {
        super(baseView);
        setInitPage(1);

    }

    @Override
    protected void initAdapter() {
        mAliPlantAdapter = new AliPlantAdapter(currentList);

    }

    @Override
    public void request(boolean readCache) {
        mModel.request(currentPage, mView.getId());
    }

    @Override
    protected IImageDetilsModel setModel() {
        return new ImageDetilsModelImpl(this);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        GridLayoutManager manager = new GridLayoutManager(mView.getViewContext(), 2, 1, false);
        mView.bindAdapter(mAliPlantAdapter);
        mView.bindLayoutManager(manager);
        mAliPlantAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMore();
            }
        });
        mAliPlantAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startOperaActivity(position);
            }
        });
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return mAliPlantAdapter;
    }


    @Override
    public void onSuccess(List<PlantImage> t) {
        super.onSuccess(t);
        mView.setHeadImageUrl(t.get(0).getImage_url());
        mAliImages = t;
    }


    private void startOperaActivity(int position) {
        List<Image> imageUrls = mAliImages.get(position).getImage_list();
        String json = new Gson().toJson(imageUrls);
        Intent intent = ImageChoseActivity.getDateIntent(json);
        mView.getViewContext().startActivity(intent);
    }
}
