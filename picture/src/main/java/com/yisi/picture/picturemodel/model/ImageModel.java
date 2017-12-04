package com.yisi.picture.picturemodel.model;

import com.kinvey.android.store.DataStore;
import com.kinvey.java.Query;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.model.inter.IImageModel;
import com.yisi.picture.picturemodel.net.KinveyHelpCallback;
import com.yisi.picture.picturemodel.presenter.inter.IImagePresenter;

import java.util.List;

/**
 * Created by chenql on 2017/10/14.
 */

public class ImageModel extends BaseModelImpl<IImagePresenter> implements IImageModel {

    public ImageModel(IImagePresenter basePresenter) {
        super(basePresenter);
    }

    /**
     * @param id 什么种类的散图
     */
    @Override
    public void request(int id, int page) {
        DataStore<Image> dataStore = DataStore.collection("Image", Image.class, StoreType.NETWORK, YiSiApplication.getKinveyClient());
        Query query = dataStore.query().setLimit(10).setSkip((page) * 10).equals("type_id", id);
        dataStore.find(query, new KinveyHelpCallback<Image>() {
            @Override
            public void onDataSuccess(List<Image> list) {
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
