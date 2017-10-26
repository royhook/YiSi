package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.PictureCollectAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.database.table.PictureTable;

import java.util.List;

/**
 * Created by chenql on 2017/5/4.
 * 图片收藏Fragment
 */

public class PictureCollectFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    private List<Image> mImages;

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
                mImages = PictureTable.getInstance().queryAllImage();
                if (mImages == null || mImages.size() == 0) {
                    onEmpty();
                    return;
                }
                onLoadingSuccess();
                PictureCollectAdapter adapter = new PictureCollectAdapter(mImages);
                GridLayoutManager manager = new GridLayoutManager(getContext(), 2, 1, false);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        startOperaActivity(position);
                    }
                });
            }
        }, 1000);


    }

    private void startOperaActivity(int position) {
        Intent intent = ImageOperateActivity.getDataIntent(position, new Gson().toJson(mImages));
        startActivity(intent);
    }
}
