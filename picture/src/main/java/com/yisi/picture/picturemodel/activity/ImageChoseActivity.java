package com.yisi.picture.picturemodel.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.ImageChoseAdapter;
import com.yisi.picture.picturemodel.bean.Image;

import java.util.List;

/**
 * Created by chenql on 2017/10/12.
 */

public class ImageChoseActivity extends BaseActivity {
    public static final String KEY_PLANT = "key_plant";
    private RecyclerView mRecyclerView;
    private ImageView mImageView;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_chose);
        mRecyclerView = findView(R.id.rv_chose);
        mImageView = findView(R.id.iv_chose);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        final String json = getIntent().getStringExtra(KEY_PLANT);
        Gson gson = new Gson();
        List<Image> images = gson.fromJson(json, new TypeToken<List<Image>>() {
        }.getType());
        ImageChoseAdapter adapter = new ImageChoseAdapter(images);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageChoseActivity.this.startActivity(ImageOperateActivity.getDataIntent(position, json));
            }
        });

    }


    public static Intent getDateIntent(String list) {
        Intent intent = new Intent();
        intent.setClass(YiSiApplication.mGlobleContext, ImageChoseActivity.class);
        intent.putExtra(KEY_PLANT, list);
        return intent;
    }
}
