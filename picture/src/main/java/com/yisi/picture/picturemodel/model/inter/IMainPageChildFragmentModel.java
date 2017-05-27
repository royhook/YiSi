package com.yisi.picture.picturemodel.model.inter;


import com.yisi.picture.baselib.base.inter.IBaseModel;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragmentModel extends IBaseModel {
    void request(int type_id, int page, boolean readCache);
}
