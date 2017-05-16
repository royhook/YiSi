package com.yisi.picture.picturemodel.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.yisi.picture.picturemodel.adapter.ImageOperatePagerAdapter;


/**
 * Created by roy on 2017/2/11.
 */

public class PinchViewPager extends ViewPager {
    ImageOperatePagerAdapter adapter;

    public PinchViewPager(Context context) {
        super(context);
    }



    public PinchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
