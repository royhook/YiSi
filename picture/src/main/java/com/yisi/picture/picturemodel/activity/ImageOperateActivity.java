package com.yisi.picture.picturemodel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.inter.IImageOperaAty;
import com.yisi.picture.picturemodel.presenter.ImageOperateOperaPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IImageOperaPre;
import com.yisi.picture.picturemodel.view.OperatePopList;
import com.yisi.picture.picturemodel.view.PinchViewPager;
import com.yisi.picture.picturemodel.view.inter.IPopListClick;


/**
 * Created by roy on 2017/1/20.
 */

public class ImageOperateActivity extends BaseActivity implements IImageOperaAty, IPopListClick {

    IImageOperaPre iImageOperaPre;
    PinchViewPager vp;
    TextView mNumTextView;
    ImageView mOperateImage;
    OperatePopList mOperatePopList;

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
        mOperateImage = findView(R.id.iv_operate);
        mOperatePopList = new OperatePopList(this);
        mOperatePopList.setPopListClickListener(this);
        mOperateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperatePopList.showPopList();
            }
        });
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

    public void refreshFeaturesState() {

    }

    @Override
    public void onDownLoadClick() {
        iImageOperaPre.downloadImg();
    }

    @Override
    public void onCollectionClick() {
        iImageOperaPre.collectImg();
    }

    @Override
    public void onSettingWallPageClick() {
        iImageOperaPre.setWallPaper();
    }

    @Override
    public void onSystemSettingWallPageClick() {
        iImageOperaPre.setSystemWallPaper();
    }
}
