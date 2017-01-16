package com.yisi.picture.model.inter;

import com.yisi.picture.base.inter.IBaseModel;

/**
 * Created by roy on 2017/2/16.
 */

public interface IPlantModel extends IBaseModel {

    void request(int page, boolean readCache);
}
