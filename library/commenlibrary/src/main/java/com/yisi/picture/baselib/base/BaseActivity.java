package com.yisi.picture.baselib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.classic.common.MultipleStatusView;
import com.yisi.picture.baselib.R;
import com.yisi.picture.baselib.base.inter.IBaseAty;

/**
 * Created by roy on 2017/1/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseAty {


    private MultipleStatusView mMultipleStatusView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out);
        initPresenter();
        initViews();
        initMultyView();
        initData();
    }

    private void initMultyView() {
        mMultipleStatusView = findView(R.id.base_multiplestatusview);
    }

    protected abstract void initPresenter();

    protected abstract void initViews();

    protected abstract void initData();

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onLoadingFail() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showError();
    }

    @Override
    public void onLoadingPage() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showLoading();
    }

    @Override
    public void onLoadingSuccess() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showContent();
    }

    @Override
    public void onEmpty() {
        if (mMultipleStatusView != null)
            mMultipleStatusView.showEmpty();
    }
}
