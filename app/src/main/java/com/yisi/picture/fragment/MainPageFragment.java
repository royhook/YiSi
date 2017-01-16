package com.yisi.picture.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.flyco.tablayout.SlidingTabLayout;
import com.yisi.picture.R;
import com.yisi.picture.adapter.MainContentPagerAdapter;
import com.yisi.picture.base.BaseFragment;
import com.yisi.picture.bean.MainContent;
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
    private ArrayList<Fragment> pagerFragment;

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
        pagerFragment = new ArrayList<>();
        pagerFragment.add(new HotFragment());
        pagerFragment.add(new NewFragment());
        pagerFragment.add(new FineFragment());
        MainContentPagerAdapter adapter = new MainContentPagerAdapter(getChildFragmentManager(), pagerFragment);
        mViewPager.setAdapter(adapter);
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
    public void initTitle(List<MainContent> mainContents) {
        mSlidingTabLayout.setViewPager(mViewPager, new String[]{"热门", "最新", "精品"}, getActivity(), pagerFragment);
    }
}
