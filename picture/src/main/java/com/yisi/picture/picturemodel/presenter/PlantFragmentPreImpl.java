package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.PlantAdapter;
import com.yisi.picture.picturemodel.bean.PlantBrowse;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.model.ImageOperaOperateModel;
import com.yisi.picture.picturemodel.model.PlantFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IPlantModel;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantFragmentPreImpl extends BaseRefreshPresenterImpl<IPlansFragment, IPlantModel, PlantBrowse> implements IPlantFragmentPre<PlantBrowse>,
        BaseQuickAdapter.OnItemClickListener {
    PlantAdapter mPlantAdapter;

    public PlantFragmentPreImpl(IPlansFragment baseView) {
        super(baseView);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        if (mPlantAdapter == null) {
            mPlantAdapter = new PlantAdapter(currentList);
            mView.getRecylerView().setLayoutManager(new GridLayoutManager(mView.getViewContext(), 2, 1, false));
            mView.getRecylerView().setAdapter(mPlantAdapter);
            mView.getSwipeRefresh().setOnRefreshListener(this);
            mPlantAdapter.setOnItemClickListener(this);
        }
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
        int id = currentList.get(position - 1).getPlant_id();
        Intent intent = new Intent(mView.getViewContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_PLANT_TYPE, id);
        intent.putExtra(IntentKey.KEY_OPEN_TYPE, ImageOperaOperateModel.TYPE_REQUEST_SHOW);
        mView.getViewContext().startActivity(intent);
    }
}
