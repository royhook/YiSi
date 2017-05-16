package com.yisi.picture.picturemodel.presenter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.baselib.adapter.BaseAdapter;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.PlantAdapter;
import com.yisi.picture.picturemodel.base.BaseRefreshPresenterImpl;
import com.yisi.picture.picturemodel.bean.PlantBrowse;
import com.yisi.picture.picturemodel.fragment.inter.IPlansFragment;
import com.yisi.picture.picturemodel.model.ImageOperaOperateModel;
import com.yisi.picture.picturemodel.model.PlantFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IPlantModel;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantFragmentPreImpl extends BaseRefreshPresenterImpl<IPlansFragment, IPlantModel, PlantBrowse> implements IPlantFragmentPre<PlantBrowse>, OnItemClickListener {
    PlantAdapter mPlantAdapter;

    public PlantFragmentPreImpl(IPlansFragment baseView) {
        super(baseView);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        if (mPlantAdapter == null) {
            mPlantAdapter = new PlantAdapter(currentList);
            getRecyclerView().setLayoutManager(new GridLayoutManager(mView.getViewContext(), 2, 1, false));
            getRecyclerView().setAdapter(mPlantAdapter);
            getRecyclerView().setLoadingListener(this);
            mPlantAdapter.setOnItemClickListener(this);
        }
    }

    @Override
    public BaseAdapter getRefreshAdapter() {
        return mPlantAdapter;
    }

    @Override
    protected XRecyclerView getRecyclerView() {
        return mView.getRecylerView();
    }

    @Override
    protected PlantFragmentModelImpl setModel() {
        return new PlantFragmentModelImpl(this);
    }


    @Override
    public void onClick(View view, int position) {
        int id = currentList.get(position - 1).getPlant_id();
        Intent intent = new Intent(mView.getViewContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_PLANT_TYPE, id);
        intent.putExtra(IntentKey.KEY_OPEN_TYPE, ImageOperaOperateModel.TYPE_REQUEST_SHOW);
        mView.getViewContext().startActivity(intent);
    }


    @Override
    public void request(boolean readCache) {
        mModel.request(currentPage, readCache);
    }

    @Override
    public void onEmpty() {
        if (currentList != null && currentList.size() == 0) {
            mView.onEmpty();
        }
        mView.dataOut();
    }
}
