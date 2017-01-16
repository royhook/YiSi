package com.yisi.picture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by roy on 2017/1/14.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
