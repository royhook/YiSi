package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.AlbumImage;
import com.yisi.picture.model.inter.IAlbumAtyModel;
import com.yisi.picture.net.BmobRequest;
import com.yisi.picture.presenter.inter.IAlbumAtyPre;
import com.yisi.picture.utils.LogUtils;

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
