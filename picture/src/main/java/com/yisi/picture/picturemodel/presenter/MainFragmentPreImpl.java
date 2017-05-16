package com.yisi.picture.picturemodel.presenter;

import android.support.v4.app.Fragment;

import com.yisi.picture.picturemodel.base.BasePresenterImpl;
import com.yisi.picture.picturemodel.bean.MainPage;
import com.yisi.picture.picturemodel.bean.PlantBrowse;
import com.yisi.picture.picturemodel.fragment.MainPageChildFragment;
import com.yisi.picture.picturemodel.fragment.inter.IMainFragment;
import com.yisi.picture.picturemodel.model.MainFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IMainFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IMainFragmentPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainFragmentPreImpl extends BasePresenterImpl<IMainFragment, IMainFragmentModel> implements IMainFragmentPre {


    private MainPageChildFragment mainPageChildFragment;
    private ArrayList<Fragment> fragments;

    public MainFragmentPreImpl(IMainFragment baseView) {
        super(baseView);
    }

    @Override
    protected IMainFragmentModel setModel() {
        mModel = new MainFragmentModelImpl(this);
        return mModel;
    }

    @Override
    public void requestBannerAndShow() {
        mModel.requestBannerData();
    }

    @Override
    public void requestContentAndShow() {
        mModel.requestContentData();
        mView.onLoadingPage();
    }

    @Override
    public void onRecoverState(int currentFragment) {
//        MainPageChildFragment mainPageFragment = (MainPageChildFragment) fragments.get(currentFragment);
//        if (mainPageFragment != null)
//            mainPageFragment.getmRecyclerView().smoothScrollToPosition(0);

    }

    @Override
    public void onEmpty() {
        mView.onEmpty();
    }

    @Override
    public void onFail() {
        mView.onLoadingFail();
    }


    @Override
    public void onSliderBannerSuccess(List<PlantBrowse> mainSliderBanners) {
        mView.initSliderBannerData(mainSliderBanners);
    }

    @Override
    public void onContentSuccess(List<MainPage> mainContents) {
//        String[] titles = new String[mainContents.size()];
//        fragments = new ArrayList<>();
//        for (int i = 0; i < mainContents.size(); i++) {
//            titles[i] = mainContents.get(i).getTitle();
//        }
//        mainPageChildFragment = new MainPageChildFragment();
//        mainPageChildFragment.setType_id(mainContents.get(0).getType_id());
//        fragments.add(mainPageChildFragment);
//        AlbumFragment albumFragment = new AlbumFragment();
//        PlantFragment plantFragment = new PlantFragment();
//        fragments.add(albumFragment);
//        fragments.add(plantFragment);
//        mView.initMainPageChildPage(titles, fragments);
        mView.onLoadingSuccess();
    }
}
