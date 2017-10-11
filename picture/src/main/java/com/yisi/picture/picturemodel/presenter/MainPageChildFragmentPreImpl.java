package com.yisi.picture.picturemodel.presenter;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.base.BaseRefreshPresenterImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.activity.ImageDetilsActivity;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.MainPageChildAliAdapter;
import com.yisi.picture.picturemodel.bean.PlantModel;
import com.yisi.picture.picturemodel.fragment.inter.IMainPageChildFragment;
import com.yisi.picture.picturemodel.model.MainPageChildFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentPreImpl extends BaseRefreshPresenterImpl<IMainPageChildFragment, IMainPageChildFragmentModel, PlantModel> implements
        IMainPageChildFragmentPre {
    private MainPageChildAliAdapter adapter;

    public MainPageChildFragmentPreImpl(IMainPageChildFragment baseView) {
        super(baseView);
    }

    @Override
    protected void initAdapter() {
        adapter = new MainPageChildAliAdapter(currentList);
    }

    @Override
    public void bindLayouManagerAndAdapter() {
        //初始化头部adapter
        adapter.setOnRecommandClickListener(new OnTypeClickListener());
        adapter.setEnableLoadMore(false);
        adapter.addFooterView(mView.getLastView());
        mView.getRecycleView().setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mView.getRecycleView().getContext(), LinearLayoutManager.VERTICAL, false);
        mView.getRecycleView().setLayoutManager(layoutManager);
    }

    @Override
    public BaseQuickAdapter getRefreshAdapter() {
        return adapter;
    }

    @Override
    protected IMainPageChildFragmentModel setModel() {
        return new MainPageChildFragmentModelImpl(this);
    }


    @Override
    public void request(boolean cache) {
        mModel.request();
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


    private class OnTypeClickListener implements MainPageChildAliAdapter.OnTypeClickListener {

        @Override
        public void onClick(int id, String name) {
            Intent intent = new Intent(mView.getRecycleView().getContext(), ImageDetilsActivity.class);
            intent.putExtra(IntentKey.KEY_KIND_ID, id);
            intent.putExtra(IntentKey.kEY_KIND_NAME, name);
            mView.getRecycleView().getContext().startActivity(intent);
        }
    }

    private class onImageClickListener implements BaseQuickAdapter.OnItemClickListener {

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
}
