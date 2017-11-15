package com.yisi.picture.picturemodel.model;


import com.kinvey.android.store.DataStore;
import com.kinvey.java.Query;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.RecommandPlantImage;
import com.yisi.picture.picturemodel.model.inter.IPlantModel;
import com.yisi.picture.picturemodel.net.KinveyHelpCallback;
import com.yisi.picture.picturemodel.presenter.inter.IPlantFragmentPre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantFragmentModelImpl extends BaseModelImpl<IPlantFragmentPre<RecommandPlantImage>> implements IPlantModel {

    public PlantFragmentModelImpl(IPlantFragmentPre<RecommandPlantImage> basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int page, boolean readCache) {
        DataStore<RecommandPlantImage> dataStore = DataStore.collection("RecommandPlantImage", RecommandPlantImage.class, StoreType.CACHE, YiSiApplication
                .getKinveyClient());
        Query query = dataStore.query().setSkip(page * 10).setLimit(10);
        dataStore.find(query, new KinveyHelpCallback<RecommandPlantImage>() {
            @Override
            public void onDataSuccess(List<RecommandPlantImage> list) {
                //过滤出需要轮播的
                List<RecommandPlantImage> rotateList = new ArrayList<>();
                List<RecommandPlantImage> normalList = new ArrayList<>();
                for (RecommandPlantImage image : list) {
                    //需要轮播的
                    if (image.isRotation()) {
                        rotateList.add(image);
                    }
                    //普通的
                    else {
                        normalList.add(image);
                    }
                }
                mPresenter.bindBanner(rotateList);
                mPresenter.onSuccess(normalList);
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
