package com.yisi.picture.presenter;

import com.yisi.picture.base.BasePresenterImpl;
import com.yisi.picture.bean.YiSiImage;
import com.yisi.picture.fragment.inter.IMainPageChildFragment;
import com.yisi.picture.model.MainPageChildFragmentModelImpl;
import com.yisi.picture.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.presenter.inter.IMainPageChildFragmentPre;
import com.yisi.picture.utils.LogUtils;

import java.util.List;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentPreImpl extends BasePresenterImpl<IMainPageChildFragment, IMainPageChildFragmentModel> implements IMainPageChildFragmentPre {

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
                    //先判断url是否相等
                    if (yiSiImages.get(0).getImg_url().equals(mView.getYiSiImages().get(0).getImg_url())) {
                        mView.onNoLastestData();
                        return;
                    }
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
    }

    @Override
    public void request(int type_id, int page, boolean readCache) {
        mModel.request(type_id, page, readCache);
    }
}
