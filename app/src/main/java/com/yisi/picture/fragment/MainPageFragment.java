package com.yisi.picture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.flyco.tablayout.SlidingTabLayout;
import com.yisi.picture.R;
import com.yisi.picture.adapter.MainContentPagerAdapter;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.bean.MainSliderBanner;
import com.yisi.picture.fragment.inter.IMainFragment;
import com.yisi.picture.presenter.MainFragmentPreImpl;
import com.yisi.picture.presenter.inter.IMainFragmentPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */

public class MainPageFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, IMainFragment {

    private SliderLayout mSliderLayout;
    private SlidingTabLayout mSlidingTabLayout;
    private IMainFragmentPre mainFragmentPre;
    private ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFragmentPre = new MainFragmentPreImpl(this);

    }

    @Override
    protected void initViews() {
        mSliderLayout = findview(R.id.main_fragment_slider);
        mSlidingTabLayout = findview(R.id.main_fragment_slidertab);
        mViewPager = findview(R.id.main_fragment_vp_content);
        AppBarLayout mAppBarLayout = findview(R.id.main_appbar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (getResources().getDimensionPixelOffset(R.dimen.px600) == Math.abs(verticalOffset)) {
                    if (mSliderLayout.getVisibility() == View.VISIBLE) {
                        mSliderLayout.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected int getContentResouce() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        mainFragmentPre.requestBannerAndShow();
        mainFragmentPre.requestContentAndShow();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void initSliderBannerData(List<MainSliderBanner> mainSliderBanners) {
        for (int i = 0; i < mainSliderBanners.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.description(mainSliderBanners.get(i).getmDescription())
                    .image(mainSliderBanners.get(i).getmImgUrl())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            mSliderLayout.addSlider(textSliderView);
        }

        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Tablet);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
    }

    @Override
    public void initMainPageChildPage(String[] titles, ArrayList<Fragment> fragments) {
        MainContentPagerAdapter adapter = new MainContentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mSlidingTabLayout.setViewPager(mViewPager, titles);
    }
}
