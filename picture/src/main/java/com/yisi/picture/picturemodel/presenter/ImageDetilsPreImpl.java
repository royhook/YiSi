package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.activity.inter.IImageDetilsAty;
import com.yisi.picture.picturemodel.adapter.AliPlantAdapter;
import com.yisi.picture.picturemodel.bean.AliImage;
import com.yisi.picture.picturemodel.bean.AliImageUrl;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.model.ImageDetilsModelImpl;
import com.yisi.picture.picturemodel.model.inter.IImageDetilsModel;
import com.yisi.picture.picturemodel.presenter.inter.IDetilsPre;

import java.util.ArrayList;
import java.util.List;

import static com.yisi.picture.picturemodel.model.ImageOperaOperateModel.TYPE_ONLY_SHOW;

/**
 * Created by chenql on 2017/6/1.
 */

public class ImageDetilsPreImpl extends BaseRefreshPresenterImpl<IImageDetilsAty, IImageDetilsModel, AliImage> implements IDetilsPre {

    AliPlantAdapter mAliPlantAdapter;
    List<AliImage> mAliImages;

    public ImageDetilsPreImpl(IImageDetilsAty baseView) {
        super(baseView);
        setInitPage(1);

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
        if (mAliPlantAdapter == null) {
            mAliPlantAdapter = new AliPlantAdapter(currentList);
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
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return mAliPlantAdapter;
    }


    @Override
    public void onSuccess(List<AliImage> t) {
        super.onSuccess(t);
        mView.setHeadImageUrl(t.get(0).getList().get(1).getBig());
        mAliImages = t;
    }


    private void startOperaActivity(int position) {
        List<AliImageUrl> imageUrls = mAliImages.get(position).getList();
        List<YiSiImage> yiSiImages = new ArrayList<>();
        for (AliImageUrl aliImageUrl : imageUrls) {
            YiSiImage yiSiImage = new YiSiImage();
            yiSiImage.setImg_url(aliImageUrl.getBig());
            yiSiImages.add(yiSiImage);
        }
        Intent intent = new Intent(mView.getViewContext(), ImageOperateActivity.class);
        String json = new Gson().toJson(yiSiImages);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA, json);
        intent.putExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, 0);
        intent.putExtra(IntentKey.KEY_OPEN_TYPE, TYPE_ONLY_SHOW);
        mView.getViewContext().startActivity(intent);
    }
}
