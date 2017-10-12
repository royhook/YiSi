package com.yisi.picture.picturemodel.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.viewholder.ImageChoseViewHolder;
import com.yisi.picture.picturemodel.bean.Image;

import java.util.List;

/**
 * Created by chenql on 2017/10/12.
 */

public class ImageChoseAdapter extends BaseQuickAdapter<Image, ImageChoseViewHolder> {


    public ImageChoseAdapter(@Nullable List<Image> data) {
        super(R.layout.adapter_image, data);
    }

    @Override
    protected void convert(ImageChoseViewHolder helper, Image item, int position) {
        GlideUtils.displayImage(item.getUrl(), helper.getImageView());
    }
}
