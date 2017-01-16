package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.MainPage;
import com.yisi.picture.bean.MainSliderBanner;
import com.yisi.picture.model.inter.IMainFragmentModel;
import com.yisi.picture.presenter.inter.IMainFragmentPre;
import com.yisi.picture.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/1/19.
 */

public class MainFragmentModelImpl extends BaseModelImpl<IMainFragmentPre> implements IMainFragmentModel {

    public MainFragmentModelImpl(IMainFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void requestBannerData() {
        MainSliderBanner firstBanner = new MainSliderBanner();
        firstBanner.setmDescription("舒服点顺丰速递水电费电饭锅发个发");
        firstBanner.setmImgUrl("http://h.hiphotos.baidu.com/image/pic/item/a2cc7cd98d1001e9460fd63bbd0e7bec54e797d7.jpg");

        MainSliderBanner secondBanner = new MainSliderBanner();
        secondBanner.setmDescription("爱的色放多少分个发个水电费公司");
        secondBanner.setmImgUrl("http://c.hiphotos.baidu.com/image/pic/item/0bd162d9f2d3572c433b7ad88813632763d0c3dd.jpg");

        MainSliderBanner thridBanner = new MainSliderBanner();
        thridBanner.setmImgUrl("http://img2.imgtn.bdimg.com/it/u=3978028653,1213029441&fm=23&gp=0.jpg");
        thridBanner.setmDescription("东方故事光伏发电哥发电公司发个");

        List<MainSliderBanner> mainSliderBanners = new ArrayList<>();
        mainSliderBanners.add(firstBanner);
        mainSliderBanners.add(secondBanner);
        mainSliderBanners.add(thridBanner);

        mPresenter.onSliderBannerSuccess(mainSliderBanners);
    }

    @Override
    public void requestContentData() {
        BmobQuery<MainPage> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<MainPage>() {
            @Override
            public void done(List<MainPage> list, BmobException e) {
                if (e == null) {
                    mPresenter.onContentSuccess(list);
                } else {
                    LogUtils.d("error");
                }
            }
        });
    }
}
