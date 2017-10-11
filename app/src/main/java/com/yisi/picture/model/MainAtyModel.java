package com.yisi.picture.model;

import com.kinvey.android.store.DataStore;
import com.kinvey.java.store.StoreType;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.baselib.utils.PreferenceKey;
import com.yisi.picture.baselib.utils.PreferencesUtils;
import com.yisi.picture.model.inter.IMainAtyModel;
import com.yisi.picture.picturemodel.bean.GlobalSetting;
import com.yisi.picture.picturemodel.net.KinveyHelpCallback;
import com.yisi.picture.presenter.inter.IMainAtyPre;

import java.util.List;

/**
 * Created by chenql on 2017/3/28.
 */

public class MainAtyModel extends BaseModelImpl<IMainAtyPre> implements IMainAtyModel {
    public MainAtyModel(IMainAtyPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request() {
        //请求全局接口
        DataStore<GlobalSetting> dataStore = DataStore.collection("GlobalSetting", GlobalSetting.class, StoreType.NETWORK, YiSiApplication.getKinveyClient());
        dataStore.find(new KinveyHelpCallback<GlobalSetting>() {
            @Override
            public void onDataSuccess(List<GlobalSetting> list) {
                GlobalSetting globalSetting = list.get(0);
                if (globalSetting != null) {
                    int data = globalSetting.getRecommand_data();
                    PreferencesUtils.putInt(YiSiApplication.mGlobleContext, PreferenceKey.MY_RECOMMAND_DATA, data);
                }
            }

            @Override
            public void onFail(Throwable throwable) {
            }

            @Override
            public void onEmpty() {
            }
        });
    }
}
