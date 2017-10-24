package com.yisi.picture.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yisi.picture.R;
import com.yisi.picture.activity.inter.ICollectAty;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.picturemodel.adapter.CollectionPagerAdapter;
import com.yisi.picture.picturemodel.fragment.PictureCollectFragment;
import com.yisi.picture.picturemodel.fragment.PlantCollectFragment;

import java.util.ArrayList;

/**
 * Created by chenql on 2017/5/4.
 */

public class CollectionActivity extends BaseActivity implements ICollectAty {
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;
    String titles[];
    ArrayList<Fragment> mFragments;
    ImageView mCloseView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_start_in, R.anim.activity_start_out);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_collect);
        mSlidingTabLayout = findView(R.id.collect_activity_slidertab);
        mViewPager = findView(R.id.collect_activity_vp_content);
        mCloseView = findView(R.id.collect_activity_back);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionActivity.this.finish();
            }
        });

    }

    @Override
    protected void initData() {
        String imgeCollect = this.getResources().getString(R.string.collect_image);
        String plantCollect = getResources().getString(R.string.collect_plant);
        titles = new String[]{imgeCollect, plantCollect};
        mFragments = new ArrayList<>();
        mFragments.add(new PictureCollectFragment());
        mFragments.add(new PlantCollectFragment());
        CollectionPagerAdapter adapter = new CollectionPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
        mSlidingTabLayout.setViewPager(mViewPager, titles);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_finish_in, R.anim.activity_finish_out);
    }
}
