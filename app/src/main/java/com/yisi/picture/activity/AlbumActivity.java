package com.yisi.picture.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.IAlbumAty;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.presenter.AlbumAtyPreImpl;
import com.yisi.picture.presenter.inter.IAlbumAtyPre;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumActivity extends BaseActivity implements IAlbumAty {

    private IAlbumAtyPre mAlbumAtyPre;
    private XRecyclerView mXRecyclerView;

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
    }

    @Override
    protected void initData() {
        mAlbumAtyPre.request(true);
    }

    @Override
    public XRecyclerView getRecyclerView() {
        return mXRecyclerView;
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
    public void onLoadingPage() {

    }

    @Override
    public void onLoadingSuccess() {

    }

    @Override
    public void onLoadingFail() {

    }
}
