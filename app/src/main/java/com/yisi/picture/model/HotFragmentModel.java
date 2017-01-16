package com.yisi.picture.model;

import com.yisi.picture.base.BaseModelImpl;
import com.yisi.picture.bean.HotImage;
import com.yisi.picture.model.inter.IHotFragmentModel;
import com.yisi.picture.presenter.inter.IHotFragmentPre;
import com.yisi.picture.utils.LogUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/1/19.
 */

public class HotFragmentModel extends BaseModelImpl<IHotFragmentPre> implements IHotFragmentModel {

    public HotFragmentModel(IHotFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request() {
        BmobQuery<HotImage> query = new BmobQuery<>();
        query.findObjects(new FindListener<HotImage>() {
            @Override
            public void done(List<HotImage> list, BmobException e) {
                LogUtils.d("done");
                if (e == null) {
                    mPresenter.onSuccess(list);
                } else {
                    mPresenter.onError(e.getErrorCode());
                }
            }
        });
    }
}
