package com.yisi.picture.picturemodel.fragment;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageChoseActivity;
import com.yisi.picture.picturemodel.adapter.AliPlantAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.bean.PlantImage;
import com.yisi.picture.picturemodel.database.PlantDatabase;

import java.util.List;

/**
 * Created by chenql on 2017/5/4.
 */

public class PlantCollectFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    private List<PlantImage> mPlants;

    @Override
    protected void initViews() {
        mRecyclerView = findview(R.id.collect_activity_rv);
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_collect_tao;
    }

    @Override
    protected void initData() {
        YiSiApplication.postDelay(new Runnable() {
            @Override
            public void run() {
                onLoadingSuccess();
            }
        }, 2000);
        mPlants = PlantDatabase.getInstance().queryAllPlant();
        AliPlantAdapter adapter = new AliPlantAdapter(mPlants);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, 1, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startOperaActivity(position);
            }
        });
    }

    private void startOperaActivity(int position) {
        PlantImage image = mPlants.get(position);
        List<Image> imageUrls = image.getImage_list();
        String json = new Gson().toJson(imageUrls);
        Intent intent = ImageChoseActivity.getDateIntent(json, image.getId(), image.getName(), image.getImage_url(), true);
        startActivity(intent);
    }
}
