package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.PlantBrowse;
import com.yisi.picture.model.inter.IPlantModel;
import com.yisi.picture.net.BmobRequest;
import com.yisi.picture.presenter.inter.IPlantFragmentPre;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/2/16.
 */

public class PlantFragmentModelImpl extends BaseModelImpl<IPlantFragmentPre<PlantBrowse>> implements IPlantModel {

    public PlantFragmentModelImpl(IPlantFragmentPre<PlantBrowse> basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int page, boolean readCache) {

        new BmobRequest.Builder()
                .setReadCache(readCache)
                .build()
                .request(new FindListener<PlantBrowse>() {
                    @Override
                    public void done(List<PlantBrowse> list, BmobException e) {
                        if (e == null) {
                            mPresenter.onSuccess(list);
                        } else
                            mPresenter.onError(e.getErrorCode());
                    }
                });
    }
}
