package com.yisi.picture.picturemodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.flyco.tablayout.SlidingTabLayout;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.activity.ImageOperateActivity;
import com.yisi.picture.picturemodel.adapter.MainContentPagerAdapter;
import com.yisi.picture.picturemodel.base.BaseFragment;
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
    private boolean mHasPictureMode = false;
    ImageView mImageView;
    private MainContentPagerAdapter mAdapter;
    private onLeftViewClick mOnLeftViewClick;

    public void setOnLeftViewClick(onLeftViewClick onLeftViewClick) {
        mOnLeftViewClick = onLeftViewClick;
    }

    public boolean isHasPictureMode() {
        return mHasPictureMode;
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
        mImageView = findview(R.id.iv_fragment_main_cate);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLeftViewClick != null)
                    mOnLeftViewClick.onClick();
            }
        });
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
        String[] titles = new String[]{"推荐", "分类", "专辑"};
        fragments = new ArrayList<>();

        MainPageChildFragment mainPageChildFragment = new MainPageChildFragment();
        mainPageChildFragment.setType_id(1);
        fragments.add(mainPageChildFragment);
        AlbumFragment albumFragment = new AlbumFragment();
        PlantFragment plantFragment = new PlantFragment();
        fragments.add(albumFragment);
        fragments.add(plantFragment);

        mAdapter = new MainContentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager, titles);
        onLoadingSuccess();
    }

    public interface onLeftViewClick {
        void onClick();
    }
}
