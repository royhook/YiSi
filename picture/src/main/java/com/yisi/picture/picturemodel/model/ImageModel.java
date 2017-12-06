package com.yisi.picture.picturemodel.model;

import com.kinvey.android.store.DataStore;
import com.kinvey.java.Query;
import com.kinvey.java.query.AbstractQuery;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.bean.TypeImage;
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
    public void request(final int id, int page) {
        DataStore<TypeImage> dataStore = DataStore.collection("TypeImage", TypeImage.class, StoreType.NETWORK, YiSiApplication.getKinveyClient());
        Query query = dataStore.query().addSort("_kmd", AbstractQuery.SortOrder.DESC).setLimit(10).setSkip((page) * 10).equals("type_id", id);
        dataStore.find(query, new KinveyHelpCallback<TypeImage>() {
            @Override
            public void onDataSuccess(List<TypeImage> list) {
                LogUtils.d("success:" + id);
                mPresenter.onSuccess(list);
            }

            @Override
            public void onFail(Throwable throwable) {
                mPresenter.onFail(1);
                LogUtils.d("onFail:" + id);

            }

            @Override
            public void onEmpty() {
                mPresenter.onEmpty();
                LogUtils.d("onEmpty:" + id);

            }
        });
    }
}
