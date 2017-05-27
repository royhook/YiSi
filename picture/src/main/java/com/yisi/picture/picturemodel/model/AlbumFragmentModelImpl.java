package com.yisi.picture.picturemodel.model;


import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.picturemodel.bean.Album;
import com.yisi.picture.picturemodel.model.inter.IAlbumFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IAlbumFragmentPre;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/1/23.
 */

public class AlbumFragmentModelImpl extends BaseModelImpl<IAlbumFragmentPre> implements IAlbumFragmentModel {

    public AlbumFragmentModelImpl(IAlbumFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request() {
        BmobQuery<Album> albumBmobQuery = new BmobQuery<>();
        albumBmobQuery.findObjects(new FindListener<Album>() {
            @Override
            public void done(List<Album> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0)
                        mPresenter.onSuccess(list);
                    else
                        mPresenter.onEmpty();
                } else {
                    mPresenter.onFail(e.getErrorCode());
                }
            }
        });
    }
}
