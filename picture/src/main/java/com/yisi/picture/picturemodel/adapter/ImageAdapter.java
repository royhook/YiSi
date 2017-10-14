package com.yisi.picture.picturemodel.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.Image;

import java.util.List;

/**
 * Created by roy on 2017/2/16.
 */

public class ImageAdapter extends BaseQuickAdapter<Image, BaseViewHolder> {


    public ImageAdapter(List<Image> dataList) {
        super(R.layout.adapter_image, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, Image item, int position) {
        ImageView imageView = helper.getView(R.id.adapter_plant_img);
        if (position % 2 != 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.leftMargin = imageView.getContext().getResources().getDimensionPixelOffset(R.dimen.px1);
            imageView.setLayoutParams(layoutParams);
        }
        GlideUtils.displayImage(mData.get(position).getUrl(), imageView, R.mipmap.defult);
    }
}
