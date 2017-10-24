package com.yisi.picture.picturemodel.model;


import com.kinvey.android.store.DataStore;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.PlantModel;
import com.yisi.picture.picturemodel.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.picturemodel.net.KinveyHelpCallback;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentModelImpl extends BaseModelImpl<IMainPageChildFragmentPre> implements IMainPageChildFragmentModel {

    public MainPageChildFragmentModelImpl(IMainPageChildFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request() {
        DataStore<PlantModel> dataStore = DataStore.collection("PlantModel", PlantModel.class, StoreType.NETWORK, YiSiApplication.getKinveyClient());
        dataStore.find(new KinveyHelpCallback<PlantModel>() {
            @Override
            public void onDataSuccess(List<PlantModel> list) {
                Collections.sort(list, new Comparator<PlantModel>() {
                    public int compare(PlantModel arg0, PlantModel arg1) {
                        Integer time0 = arg0.getWeight();
                        Integer time1 = arg1.getWeight();
                        return time1.compareTo(time0);
                    }
                });
                mPresenter.onSuccess(list);

            }

            @Override
            public void onFail(Throwable throwable) {
                mPresenter.onFail(1);
            }

            @Override
            public void onEmpty() {
                mPresenter.onEmpty();
            }
        });
    }
}
