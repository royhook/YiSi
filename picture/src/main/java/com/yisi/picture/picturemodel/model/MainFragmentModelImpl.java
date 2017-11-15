package com.yisi.picture.picturemodel.model;

import com.kinvey.android.store.DataStore;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.MainTab;
import com.yisi.picture.picturemodel.model.inter.IMainFragmentModel;
import com.yisi.picture.picturemodel.net.KinveyHelpCallback;
import com.yisi.picture.picturemodel.presenter.inter.IMainFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainFragmentModelImpl extends BaseModelImpl<IMainFragmentPre> implements IMainFragmentModel {

    public MainFragmentModelImpl(IMainFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request() {
        DataStore<MainTab> dataStore = DataStore.collection("MainTab", MainTab.class, StoreType.CACHE, YiSiApplication.getKinveyClient());
        dataStore.find(new KinveyHelpCallback<MainTab>() {
            @Override
            public void onDataSuccess(List<MainTab> list) {
                mPresenter.onSuccess(list);
            }

            @Override
            public void onFail(Throwable throwable) {
                mPresenter.onFail();
            }

            @Override
            public void onEmpty() {
                mPresenter.onEmpty();
            }
        });
    }
}
