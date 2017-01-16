package com.yisi.picture.presenter.inter;

import com.yisi.picture.base.inter.IBasePresenter;
import com.yisi.picture.bean.Album;

import java.util.List;

/**
 * Created by roy on 2017/1/23.
 */

public interface IAlbumFragmentPre extends IBasePresenter {
    void request(int type);

    void onSuccess(List<Album> albumList);

    void onFail(int errorCode);
}
