package com.yisi.picture.presenter;

import com.yisi.picture.base.BasePresenterImpl;
import com.yisi.picture.bean.HotImage;
import com.yisi.picture.fragment.inter.IHotFragment;
import com.yisi.picture.model.HotFragmentModel;
import com.yisi.picture.model.inter.IHotFragmentModel;
import com.yisi.picture.presenter.inter.IHotFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class HotFragmentPreImpl extends BasePresenterImpl<IHotFragment, IHotFragmentModel> implements IHotFragmentPre {

    protected HotFragmentPreImpl(IHotFragment baseView) {
        super(baseView);
    }

    @Override
    protected IHotFragmentModel setModel() {
        return new HotFragmentModel(this);
    }

    @Override
    public void onSuccess(List<HotImage> hotImages) {

    }

    @Override
    public void onError(int errorCode) {

    }
}
