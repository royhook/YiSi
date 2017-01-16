package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.YiSiImage;
import com.yisi.picture.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.net.BmobRequest;
import com.yisi.picture.presenter.inter.IMainPageChildFragmentPre;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentModelImpl extends BaseModelImpl<IMainPageChildFragmentPre> implements IMainPageChildFragmentModel {

    public MainPageChildFragmentModelImpl(IMainPageChildFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int type_id, int page, boolean readCache) {
        new BmobRequest.Builder()
                .addEqualTo("type_id", type_id)
                .addEqualTo("page", page)
                .setReadCache(readCache)
                .build()
                .request(new FindListener<YiSiImage>() {
                    @Override
                    public void done(List<YiSiImage> list, BmobException e) {
                        if (e == null) {
                            mPresenter.onSuccess(list);
                        } else
                            mPresenter.onError(e.getErrorCode());
                    }
                });
    }
}
