package com.yisi.picture.picturemodel.model;


import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.AlbumImage;
import com.yisi.picture.picturemodel.model.inter.IAlbumAtyModel;
import com.yisi.picture.picturemodel.net.BmobRequest;
import com.yisi.picture.picturemodel.presenter.inter.IAlbumAtyPre;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/2/5.
 */

public class AlbumAtyModelImpl extends BaseModelImpl<IAlbumAtyPre<AlbumImage>> implements IAlbumAtyModel {

    public AlbumAtyModelImpl(IAlbumAtyPre<AlbumImage> basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int type_id, int page, boolean readCache) {

        new BmobRequest.Builder()
                .setReadCache(readCache)
                .setSkip(page * 10)
                .addEqualTo("type_id", type_id)
                .build()
                .request(new FindListener<AlbumImage>() {
                    @Override
                    public void done(List<AlbumImage> list, BmobException e) {
                        if (e == null) {
                            if (list != null) {
                                if (list.size() != 0)
                                    mPresenter.onSuccess(list);
                                else
                                    mPresenter.onEmpty();
                            }
                        } else {
                            mPresenter.onFail(e.getErrorCode());
                            LogUtils.d(e.toString());
                        }
                    }
                });
    }


}
