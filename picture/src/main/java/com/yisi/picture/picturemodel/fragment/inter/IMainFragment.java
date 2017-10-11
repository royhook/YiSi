package com.yisi.picture.picturemodel.fragment.inter;

import com.yisi.picture.baselib.base.inter.IBaseAty;
import com.yisi.picture.picturemodel.bean.MainTab;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainFragment extends IBaseAty {


    void initMainPageChildPage(List<MainTab> mainTabs);

}
