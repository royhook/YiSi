package com.yisi.picture.picturemodel.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    ImageView mCloseView;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestId = getIntent().getIntExtra(IntentKey.KEY_KIND_ID, 0);
        name = getIntent().getStringExtra(IntentKey.kEY_KIND_NAME);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLoadingSuccess() {
        super.onLoadingSuccess();
        if (mRecyclerView == null) {
            mRecyclerView = findView(R.id.rv_content_img_detils);
        }
    }

    @Override
    protected void initPresenter() {
        mIDetilsPre = new ImageDetilsPreImpl(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_img_detils_content);
        mImageView = findView(R.id.iv_img_detils);
        mCloseView = findView(R.id.iv_img_close);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String url = getIntent().getStringExtra(IntentKey.kEY_KIND_URL);
        GlideUtils.displayImage(url, mImageView);
    }

    @Override
    protected void initData() {
        onLoadingPage();
        mIDetilsPre.request(false);
    }

    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        adapter.bindToRecyclerView(mRecyclerView);
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
