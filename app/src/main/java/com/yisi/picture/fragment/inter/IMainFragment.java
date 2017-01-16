package com.yisi.picture.fragment.inter;

import android.support.v4.app.Fragment;

import com.yisi.picture.base.inter.IBaseView;
import com.yisi.picture.bean.PlantBrowse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainFragment extends IBaseView {

    void initSliderBannerData(List<PlantBrowse> mainSliderBanners);

    void initMainPageChildPage(String[] titles, ArrayList<Fragment> fragments);
}
