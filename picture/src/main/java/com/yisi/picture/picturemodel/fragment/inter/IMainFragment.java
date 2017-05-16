package com.yisi.picture.picturemodel.fragment.inter;

import com.yisi.picture.picturemodel.base.inter.IBaseAty;
import com.yisi.picture.picturemodel.bean.PlantBrowse;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainFragment extends IBaseAty {

    void initSliderBannerData(List<PlantBrowse> mainSliderBanners);

}
