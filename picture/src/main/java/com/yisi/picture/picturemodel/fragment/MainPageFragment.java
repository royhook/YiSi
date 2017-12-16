package com.yisi.picture.picturemodel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.yisi.picture.baselib.base.BaseFragment;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.MainContentPagerAdapter;
import com.yisi.picture.picturemodel.bean.MainTab;
import com.yisi.picture.picturemodel.fragment.inter.IMainFragment;
import com.yisi.picture.picturemodel.presenter.MainFragmentPreImpl;
import com.yisi.picture.picturemodel.presenter.inter.IMainFragmentPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/1/14.
 */
public class MainPageFragment extends BaseFragment implements IMainFragment {

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
        mSlidingTabLayout.setTabWidth(ViewUtils.getDimen(R.dimen.px100));
        mViewPager = findview(R.id.main_fragment_vp_content);
        mMultipleStatusView = findview(R.id.base_multiplestatusview);
    }


    @Override
    protected int getContentResouce() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        mainFragmentPre.requestTab();
    }


    @Override
    public void initMainPageChildPage(List<MainTab> mainTabs) {
        String[] titles = new String[mainTabs.size()];
        fragments = new ArrayList<>();
        MainPageChildFragment mainPageChildFragment = new MainPageChildFragment();
        PlantFragment plantFragment = new PlantFragment();
        fragments.add(plantFragment);
        fragments.add(mainPageChildFragment);
        for (int i = 0; i < mainTabs.size(); i++) {
            titles[i] = mainTabs.get(i).getTitle();
            //前2项Tab 固定
            if (i > 1) {
                ImageFragment imageFragment = new ImageFragment();
                imageFragment.setId(mainTabs.get(i).getType_id());
                fragments.add(imageFragment);
            }
        }
        mAdapter = new MainContentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager, titles);
        onLoadingSuccess();
    }

    public interface onLeftViewClick {
        void onClick();
    }
}
