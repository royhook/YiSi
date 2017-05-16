package com.yisi.picture.picturemodel.model.inter;


import com.yisi.picture.picturemodel.base.inter.IBaseModel;

/**
* Created by roy on 2017/2/5.
        */

public interface IAlbumAtyModel extends IBaseModel {

    void request(int type, int id, boolean readCache);
}
