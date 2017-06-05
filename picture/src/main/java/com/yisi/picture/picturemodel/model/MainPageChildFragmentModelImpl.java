package com.yisi.picture.picturemodel.model;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.baselib.net.HttpCallback;
import com.yisi.picture.baselib.url.Api;
import com.yisi.picture.picturemodel.bean.AliBody;
import com.yisi.picture.picturemodel.bean.AliPictureResult;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.model.inter.IMainPageChildFragmentModel;
import com.yisi.picture.picturemodel.net.BmobRequest;
import com.yisi.picture.picturemodel.presenter.inter.IMainPageChildFragmentPre;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by roy on 2017/1/19.
 */

public class MainPageChildFragmentModelImpl extends BaseModelImpl<IMainPageChildFragmentPre> implements IMainPageChildFragmentModel {

    public MainPageChildFragmentModelImpl(IMainPageChildFragmentPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int type_id, int page, boolean readCache) {
        new BmobRequest.Builder()
                .addEqualTo("type_id", type_id)
                .setSkip(10 * page)
                .setLimit(11)
                .setReadCache(readCache)
                .build()
                .request(new FindListener<YiSiImage>() {
                    @Override
                    public void done(List<YiSiImage> list, BmobException e) {
                        if (e == null) {
                            if (list.size() != 0)
                                mPresenter.onSuccess(list);
                            else {
                                mPresenter.onEmpty();
                            }
                        } else
                            mPresenter.onError(e.getErrorCode());
                    }
                });
    }

    @Override
    public void requestAli() {
        OkGo.get(Api.ALI_PICTURE_URL)
                .cacheKey("mainchildfragment")
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .execute(new HttpCallback<AliPictureResult<AliBody>>() {
                    @Override
                    public void onSuccess(AliPictureResult<AliBody> aliBodyAliPictureResult) {
                        mPresenter.onAliSuccess(aliBodyAliPictureResult.getShowapi_res_body());
                    }
                });
    }
}
