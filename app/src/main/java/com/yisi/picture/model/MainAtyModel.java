package com.yisi.picture.model;

import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.model.inter.IMainAtyModel;
import com.yisi.picture.presenter.inter.IMainAtyPre;

/**
 * Created by chenql on 2017/3/28.
 */

public class MainAtyModel extends BaseModelImpl<IMainAtyPre> implements IMainAtyModel {
    public MainAtyModel(IMainAtyPre basePresenter) {
        super(basePresenter);
    }
}
