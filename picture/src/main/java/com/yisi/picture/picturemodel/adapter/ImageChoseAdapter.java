package com.yisi.picture.picturemodel.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
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
    protected void convert(ImageChoseViewHolder helper, Image item) {
        if (helper.getPosition() != 0 || helper.getPosition() % 3 != 1) {
            View view = helper.getImageView();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
            params.leftMargin = ViewUtils.getDimen(R.dimen.px4);
            view.setLayoutParams(params);
        }
        GlideUtils.displayImage(item.getFwfhUrl(500,500), helper.getImageView());
    }
}
