package com.yisi.picture.picturemodel.model;


import com.yisi.picture.picturemodel.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.picturemodel.net.BmobRequest;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

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
                .setSkip(10 * page)
                .setLimit(10)
                .setReadCache(readCache)
                .build()
                .request(new FindListener<YiSiImage>() {
                    @Override
                    public void done(List<YiSiImage> list, BmobException e) {
                        if (e == null) {
                            if (list.size() != 0)
                                mPresenter.onSuccess(list);
                            else {
                                mPresenter.onEmpty();
                            }
                        } else
                            mPresenter.onError(e.getErrorCode());
                    }
                });
    }
}
