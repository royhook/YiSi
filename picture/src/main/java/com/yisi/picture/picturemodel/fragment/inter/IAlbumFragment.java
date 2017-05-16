package com.yisi.picture.picturemodel.fragment.inter;


import com.yisi.picture.picturemodel.base.inter.IBaseView;
import com.yisi.picture.picturemodel.bean.Album;

import java.util.List;

/**
 * Created by roy on 2017/1/23.
 */

public interface IAlbumFragment extends IBaseView {

    void bindRecycler(List<Album> albumList);
}
