package com.yisi.picture.picturemodel.presenter.inter;

import com.yisi.picture.baselib.base.inter.IBasePresenter;
import com.yisi.picture.picturemodel.bean.Album;

import java.util.List;

/**
 * Created by roy on 2017/1/23.
 */

public interface IAlbumFragmentPre extends IBasePresenter {
    void request();

    void onSuccess(List<Album> albumList);

    void onFail(int errorCode);

    void onEmpty();
}
