package com.yisi.picture.picturemodel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.inter.IAlbumAty;
import com.yisi.picture.picturemodel.presenter.AlbumAtyPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IAlbumAtyPre;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumActivity extends BaseActivity implements IAlbumAty, View.OnClickListener {

    private IAlbumAtyPre mAlbumAtyPre;
    private RecyclerView mXRecyclerView;
    private TextView mTextView;
    private ImageView mBackImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initPresenter() {
        mAlbumAtyPre = new AlbumAtyPreImpl(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_album);
        mXRecyclerView = findView(R.id.aty_album_recycler);
        mTextView = findView(R.id.aty_album_title);
        mBackImageView = findView(R.id.aty_album_back);
        mBackImageView.setOnClickListener(this);
        mAlbumAtyPre.initViewDatas();
    }


    @Override
    protected void initData() {
        onLoadingPage();
        mAlbumAtyPre.request(true);
    }

    @Override
    public Intent getAlbumIntent() {
        return getIntent();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void setToolBarTitle(String title) {
        mTextView.setText(title);
    }

    @Override
    public void dataRunOut() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.aty_album_back) {
            AlbumActivity.this.finish();
        }
    }

    @Override
    public void bindLayoutManager(LinearLayoutManager manager) {
        mXRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void bindAdapter(BaseQuickAdapter adapter) {
        mXRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onEmpty() {
        super.onEmpty();
    }
}
