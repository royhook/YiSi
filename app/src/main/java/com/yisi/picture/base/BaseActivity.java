package com.yisi.picture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yisi.picture.R;
import com.yisi.picture.base.inter.IBaseAty;

/**
 * Created by roy on 2017/1/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseAty {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out);
        initPresenter();
        initViews();
        initData();
    }


    protected abstract void initPresenter();

    protected abstract void initViews();

    protected abstract void initData();

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onLoadingFail() {

    }

    @Override
    public void onLoadingPage() {

    }

    @Override
    public void onLoadingSuccess() {

    }
}
