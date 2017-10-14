package com.yisi.picture.picturemodel.model.inter;

import com.yisi.picture.baselib.base.inter.IBaseModel;

/**
 * Created by chenql on 2017/10/14.
 */

public interface IImageModel extends IBaseModel {
    void request(int id,int page);
}
