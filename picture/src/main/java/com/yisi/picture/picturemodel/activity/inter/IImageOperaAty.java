package com.yisi.picture.picturemodel.activity.inter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.yisi.picture.baselib.base.inter.IBaseView;


/**
 * Created by roy on 2017/2/11.
 */

public interface IImageOperaAty extends IBaseView {
    Intent getChildIntent();

    void setViewPagerAdapter(PagerAdapter pagerAdapter);

    ViewPager getViewPager();

    void updataTextView(String string);
}
