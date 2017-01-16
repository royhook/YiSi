package com.yisi.picture.fragment.inter;

import com.yisi.picture.base.inter.IBaseView;
import com.yisi.picture.bean.Album;

import java.util.List;

/**
 * Created by roy on 2017/1/23.
 */

public interface IAlbumFragment extends IBaseView {

    void bindRecycler(List<Album> albumList);
}
