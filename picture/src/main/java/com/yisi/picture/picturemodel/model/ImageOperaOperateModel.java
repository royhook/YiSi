package com.yisi.picture.picturemodel.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.model.inter.IImageOperateModel;
import com.yisi.picture.picturemodel.presenter.inter.IImageOperaPre;

import java.util.List;

/**
 * Created by roy on 2017/2/11.
 */

public class ImageOperaOperateModel extends BaseModelImpl<IImageOperaPre> implements IImageOperateModel {

    public static int TYPE_ONLY_SHOW = 1;//直接展示
    public static int TYPE_REQUEST_SHOW = 2;//请求数据后展示

    public ImageOperaOperateModel(IImageOperaPre basePresenter) {
        super(basePresenter);
    }

    @Override
    public void getData(int id) {
        String json = mPresenter.getChildIntent().getStringExtra(IntentKey.KEY_IMAGE_OPERA);
        int postion = mPresenter.getChildIntent().getIntExtra(IntentKey.KEY_IMAGE_OPERA_POSITION, 0);
        Gson gson = new Gson();
        List<Image> images = gson.fromJson(json, new TypeToken<List<Image>>() {
        }.getType());
        mPresenter.onSuccess(images, postion);
    }
}
