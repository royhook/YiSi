package com.yisi.picture.picturemodel.presenter;


import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.base.BasePresenterImpl;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.fragment.inter.IMainPageChildFragment;
import com.yisi.picture.picturemodel.model.MainPageChildFragmentModelImpl;
import com.yisi.picture.picturemodel.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentPreImpl extends BasePresenterImpl<IMainPageChildFragment, IMainPageChildFragmentModel> implements IMainPageChildFragmentPre {

    int page;

    public MainPageChildFragmentPreImpl(IMainPageChildFragment baseView) {
        super(baseView);
    }

    @Override
    protected IMainPageChildFragmentModel setModel() {
        return new MainPageChildFragmentModelImpl(this);
    }

    @Override
    public void onSuccess(List<YiSiImage> yiSiImages) {
        if (yiSiImages != null) {
            if (yiSiImages.size() != 0) {
                //下拉刷新
                if (mView.isLoadMoreOrRefresh()) {
                    if (mView.getYiSiImages().size() != 0)
                        mView.getYiSiImages().clear();
                    mView.getYiSiImages().addAll(yiSiImages);
                    mView.bindRecylerViewRefresh(yiSiImages);
                } else {
                    //上拉加载
                    mView.getYiSiImages().addAll(yiSiImages);
                    mView.bindRecylerViewLoadMore(yiSiImages);
                }
            } else {
                mView.onDataRunOut();
            }
        }
    }

    @Override
    public void onError(int errorCode) {
        LogUtils.d(errorCode + "");
        refreshPage();
    }

    @Override
    public void onEmpty() {
        page = mView.getCurrentPage();
        refreshPage();
    }

    @Override
    public void request(int type_id, int page, boolean readCache) {
        mModel.request(type_id, page, readCache);
    }

    private void refreshPage() {
        page = mView.getCurrentPage();
        mView.setCurrentPage(page--);
        mView.onDataRunOut();
    }
}
