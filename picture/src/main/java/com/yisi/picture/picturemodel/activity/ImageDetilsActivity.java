package com.yisi.picture.picturemodel.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.inter.IImageDetilsAty;
import com.yisi.picture.picturemodel.presenter.ImageDetilsPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IDetilsPre;

public class ImageDetilsActivity extends BaseActivity implements IImageDetilsAty {
    IDetilsPre mIDetilsPre;
    int requestId;
    String name;
    ImageView mImageView;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestId = getIntent().getIntExtra(IntentKey.KEY_KIND_ID, 0);
        name = getIntent().getStringExtra(IntentKey.kEY_KIND_NAME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detils);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        mImageView = findView(R.id.iv_img_detils);
        mRecyclerView = findView(R.id.rv_content_img_detils);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "收藏成功", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void initPresenter() {
        mIDetilsPre = new ImageDetilsPreImpl(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        mIDetilsPre.request(false);
    }

    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setHeadImageUrl(String imageUrl) {
        GlideUtils.displayImage(imageUrl, mImageView);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public int getId() {
        return requestId;
    }
}
