package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.MainPage;
import com.yisi.picture.bean.PlantBrowse;
import com.yisi.picture.model.inter.IMainFragmentModel;
import com.yisi.picture.net.BmobRequest;
import com.yisi.picture.presenter.inter.IMainFragmentPre;
import com.yisi.picture.utils.LogUtils;

import java.util.List;

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
        new BmobRequest.Builder()
                .addEqualTo("page", -1)
                .setReadCache(true)
                .build()
                .request(new FindListener<PlantBrowse>() {
                    @Override
                    public void done(List<PlantBrowse> list, BmobException e) {
                        if (e == null) {
                            mPresenter.onSliderBannerSuccess(list);
                        } else {
                            LogUtils.d("error");
                        }
                    }
                });
    }

    @Override
    public void requestContentData() {

        new BmobRequest.Builder()
                .setReadCache(true)
                .setOrder("createdAt")
                .build()
                .request(new FindListener<MainPage>() {
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
