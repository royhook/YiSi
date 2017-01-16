package com.yisi.picture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.yisi.picture.R;
import com.yisi.picture.activity.inter.IImageOperaAty;
import com.yisi.picture.base.BaseActivity;
import com.yisi.picture.presenter.ImageOperateOperaPreImpl;
import com.yisi.picture.presenter.inter.IImageOperaPre;
import com.yisi.picture.view.PinchViewPager;

import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by roy on 2017/1/20.
 */

public class ImageOperateActivity extends BaseActivity implements IImageOperaAty {

    IImageOperaPre iImageOperaPre;
    PinchViewPager vp;
    TextView mNumTextView;
    DanmakuView danmakuView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out);

    }

    @Override
    protected void initPresenter() {
        iImageOperaPre = new ImageOperateOperaPreImpl(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_operate);
        vp = findView(R.id.activity_operate_vp);
        mNumTextView = findView(R.id.activity_operate_num);
        danmakuView = findView(R.id.activity_operate_damu);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_zoom_in, R.anim.anim_zoom_out);
    }

    @Override
    protected void initData() {
        iImageOperaPre.getData();
    }

    @Override
    public Intent getChildIntent() {
        return getIntent();
    }

    @Override
    public void setViewPagerAdapter(PagerAdapter pagerAdapter) {
        vp.setAdapter(pagerAdapter);
    }

    @Override
    public ViewPager getViewPager() {
        return vp;
    }

    @Override
    public void updataTextView(String str) {
        mNumTextView.setText(str);
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
