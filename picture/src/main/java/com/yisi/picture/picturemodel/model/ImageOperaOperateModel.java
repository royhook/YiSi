package com.yisi.picture.picturemodel.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.base.BaseModelImpl;
import com.yisi.picture.baselib.utils.IntentKey;
import com.yisi.picture.baselib.utils.LogUtils;
import com.yisi.picture.picturemodel.bean.Plant;
import com.yisi.picture.picturemodel.bean.YiSiImage;
import com.yisi.picture.picturemodel.model.inter.IImageOperateModel;
import com.yisi.picture.picturemodel.presenter.inter.IImageOperaPre;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
        int openType = mPresenter.getChildIntent().getIntExtra(IntentKey.KEY_OPEN_TYPE, TYPE_ONLY_SHOW);
        //套图是请求后展示，非套图直接展示

        //直接展示
        if (openType == TYPE_ONLY_SHOW) {
            Gson gson = new Gson();
            List<YiSiImage> images = gson.fromJson(json, new TypeToken<List<YiSiImage>>() {
            }.getType());
            mPresenter.onSuccess(images, postion);
        }
        //请求后展示
        else {
            BmobQuery<Plant> bmobquery = new BmobQuery<>();
            bmobquery.addWhereEqualTo("plant_id", id);
            bmobquery.addWhereNotEqualTo("divide", -1);
            bmobquery.findObjects(new FindListener<Plant>() {
                @Override
                public void done(List<Plant> list, BmobException e) {
                    if (e == null) {
                        if (list != null) {
                            if (list.size() == 0) {
                                mPresenter.onEmpty();
                                return;
                            }
                            List<YiSiImage> plants = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                YiSiImage yiSiImage = new YiSiImage();
                                yiSiImage.setImg_url(list.get(i).getImg_url());
                                plants.add(yiSiImage);
                            }
                            addChangeAlbum(plants);
                            mPresenter.onSuccess(plants, 1);
                        }
                    } else {
                        LogUtils.d(e.toString());
                    }
                }
            });
        }
    }

    /**
     * 增加一张作为转换页面使用的
     *
     * @param yiSiImages
     */
    private void addChangeAlbum(List<YiSiImage> yiSiImages) {
        YiSiImage yiSiImage = new YiSiImage();
        yiSiImage.setImg_url("www");
        yiSiImages.add(yiSiImage);
    }

}
