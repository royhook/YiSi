package com.yisi.picture.picturemodel.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.baselib.net.HttpCallback;
import com.yisi.picture.baselib.url.Api;
import com.yisi.picture.picturemodel.bean.AliImageBody;
import com.yisi.picture.picturemodel.bean.AliPictureResult;
import com.yisi.picture.picturemodel.model.inter.IImageDetilsModel;
import com.yisi.picture.picturemodel.presenter.inter.IDetilsPre;

/**
 * Created by chenql on 2017/6/1.
 */

public class ImageDetilsModelImpl extends BaseModelImpl<IDetilsPre> implements IImageDetilsModel {

    public ImageDetilsModelImpl(IDetilsPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void request(int page, int id) {
        HttpParams params = new HttpParams();
        params.put("page", page);
        params.put("type", id);
        OkGo.get(Api.ALI_PICTURE_DETILES_URL)
                .params(params)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .cacheKey("imagedetils" + id + page)
                .execute(new HttpCallback<AliPictureResult<AliImageBody>>() {

                    @Override
                    public void onSuccess(AliPictureResult<AliImageBody> aliImageBodyAliPictureResult) {
                        AliImageBody body = aliImageBodyAliPictureResult.getShowapi_res_body();
                        mPresenter.onSuccess(body.getPagebean().getContentlist());
                    }
                });
    }
}
