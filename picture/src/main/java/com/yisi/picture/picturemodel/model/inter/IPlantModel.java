package com.yisi.picture.picturemodel.model.inter;


import com.yisi.picture.picturemodel.base.inter.IBaseModel;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlantModel extends IBaseModel {

    void request(int page, boolean readCache);
}
