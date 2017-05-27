package com.yisi.picture.picturemodel.model;

import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.model.inter.IMainFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IMainFragmentPre;

/**
 * Created by roy on 2017/1/19.
 */

public class MainFragmentModelImpl extends BaseModelImpl<IMainFragmentPre> implements IMainFragmentModel {

    public MainFragmentModelImpl(IMainFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void requestBannerData() {
//        new BmobRequest.Builder()
//                .addEqualTo("page", -1)
//                .setCacheTime(1000 * 60 * 5)
//                .setReadCache(true)
//                .build()
//                .request(new FindListener<PlantBrowse>() {
//                    @Override
//                    public void done(List<PlantBrowse> list, BmobException e) {
//                        if (e == null) {
//                            mPresenter.onSliderBannerSuccess(list);
//                        } else {
//                            LogUtils.d("error");
//                        }
//                    }
//                });
    }

    @Override
    public void requestContentData() {

//        new BmobRequest.Builder()
//                .setReadCache(true)
//                .setOrder("createdAt")
//                .build()
//                .request(new FindListener<MainPage>() {
//                    @Override
//                    public void done(List<MainPage> list, BmobException e) {
//                        if (e == null) {
//                            mPresenter.onContentSuccess(list);
//                        } else {
//                            LogUtils.d("error");
//                        }
//                    }
//                });
    }
}
