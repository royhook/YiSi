package com.yisi.picture.model.inter;

import com.yisi.picture.base.inter.IBaseModel;

/**
 * Created by roy on 2017/1/19.
 */

public interface IMainPageChildFragmentModel extends IBaseModel {
    void request(int type_id,int page,boolean readCache);
}
