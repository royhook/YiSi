package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.Album;
import com.yisi.picture.model.inter.IAlbumFragmentModel;
import com.yisi.picture.presenter.inter.IAlbumFragmentPre;
import com.yisi.picture.utils.LogUtils;

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
    public void request(int type) {
        BmobQuery<Album> albumBmobQuery = new BmobQuery<>();
        albumBmobQuery.findObjects(new FindListener<Album>() {
            @Override
            public void done(List<Album> list, BmobException e) {
                if (e == null) {
                    LogUtils.d("album success");
                    mPresenter.onSuccess(list);
                } else {
                    mPresenter.onFail(e.getErrorCode());
                }
            }
        });
    }
}
