package com.yisi.picture.picturemodel.activity.inter;

import android.content.Context;

import com.flyco.tablayout.CommonTabLayout;
import com.yisi.picture.baselib.base.inter.IBaseView;


/**
 * Created by roy on 2017/1/14.
 */

public interface IMainAty extends IBaseView {

    Context getMainContext();

    void showMainPage();

    void showAlbumPage();

    void showPlansPage();

    CommonTabLayout getCommonTab();

}
