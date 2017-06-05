package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.flyco.tablayout.SlidingTabLayout;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.MainContentPagerAdapter;
import com.yisi.picture.picturemodel.bean.PlantBrowse;
import com.yisi.picture.picturemodel.fragment.inter.IMainFragment;
import com.yisi.picture.picturemodel.model.ImageOperaOperateModel;
import com.yisi.picture.picturemodel.presenter.MainFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IMainFragmentPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */
public class MainPageFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, IMainFragment {

    private SlidingTabLayout mSlidingTabLayout;
    private IMainFragmentPre mainFragmentPre;
    private ViewPager mViewPager;
    //    private Toolbar mToolbar;
    private ArrayList<Fragment> fragments;
    private MainContentPagerAdapter mAdapter;

    public void setOnLeftViewClick(onLeftViewClick onLeftViewClick) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFragmentPre = new MainFragmentPreImpl(this);
    }

    @Override
    protected void initViews() {
        mSlidingTabLayout = findview(R.id.main_fragment_slidertab);
        mViewPager = findview(R.id.main_fragment_vp_content);
        mMultipleStatusView = findview(R.id.base_multiplestatusview);
        initMainPageChildPage();
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
        TextSliderView textSliderView = (TextSliderView) slider;
        Intent intent = new Intent(getContext(), ImageOperateActivity.class);
        intent.putExtra(IntentKey.KEY_PLANT_TYPE, textSliderView.getPlant_id());
        intent.putExtra(IntentKey.KEY_OPEN_TYPE, ImageOperaOperateModel.TYPE_REQUEST_SHOW);
        startActivity(intent);
    }

    @Override
    public void initSliderBannerData(List<PlantBrowse> mainSliderBanners) {
    }

    public void initMainPageChildPage() {
        String[] titles = new String[]{"推荐", "精选壁纸分类", "壁纸专辑", "明星", "娱乐", "风景", "街拍", "萌宠", "校花", "日韩美女", "自然"};
        fragments = new ArrayList<>();

        MainPageChildFragment mainPageChildFragment = new MainPageChildFragment();
        fragments.add(mainPageChildFragment);
        AlbumFragment albumFragment = new AlbumFragment();
        PlantFragment plantFragment = new PlantFragment();
        ImageFragment firstImgFragment = new ImageFragment();
        ImageFragment secondImgFragment = new ImageFragment();
        ImageFragment thridImgFragment = new ImageFragment();
        ImageFragment fourthImgFragment = new ImageFragment();
        ImageFragment fifthImgFragment = new ImageFragment();
        ImageFragment schoolFlowerFragment = new ImageFragment();
        ImageFragment beautyFragment = new ImageFragment();
        ImageFragment foodFragment = new ImageFragment();
        firstImgFragment.setId(2001);
        secondImgFragment.setId(3001);
        thridImgFragment.setId(6004);
        fourthImgFragment.setId(4006);
        fifthImgFragment.setId(6002);
        schoolFlowerFragment.setId(4004);
        beautyFragment.setId(4014);
        foodFragment.setId(6006);
        fragments.add(albumFragment);
        fragments.add(plantFragment);
        fragments.add(firstImgFragment);
        fragments.add(secondImgFragment);
        fragments.add(thridImgFragment);
        fragments.add(fourthImgFragment);
        fragments.add(fifthImgFragment);
        fragments.add(schoolFlowerFragment);
        fragments.add(beautyFragment);
        fragments.add(foodFragment);

        mAdapter = new MainContentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager, titles);
        onLoadingSuccess();
    }

    public interface onLeftViewClick {
        void onClick();
    }
}
