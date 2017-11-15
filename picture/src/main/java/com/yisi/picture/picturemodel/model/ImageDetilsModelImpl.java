package com.yisi.picture.picturemodel.model;

import com.kinvey.android.store.DataStore;
import com.kinvey.java.Query;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.PlantImage;
import com.yisi.picture.picturemodel.model.inter.IImageDetilsModel;
import com.yisi.picture.picturemodel.net.KinveyHelpCallback;
import com.yisi.picture.picturemodel.presenter.inter.IDetilsPre;

import java.util.List;

/**
 * Created by chenql on 2017/6/1.
 */

public class ImageDetilsModelImpl extends BaseModelImpl<IDetilsPre> implements IImageDetilsModel {


    public ImageDetilsModelImpl(IDetilsPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int page, int id) {
        DataStore<PlantImage> dataStore = DataStore.collection("PlantImage", PlantImage.class, StoreType.CACHE, YiSiApplication.getKinveyClient());
        Query query = dataStore.query().setLimit(10).setSkip((page - 1) * 10).equals("plant_id", id);
        dataStore.find(query, new KinveyHelpCallback<PlantImage>() {
            @Override
            public void onDataSuccess(List<PlantImage> list) {
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
