package com.yisi.picture.picturemodel.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.yisi.picture.baselib.adapter.BaseViewHolder;
import com.yisi.picture.baselib.adapter.inter.OnItemClickListener;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;


/**
 * Created by chenql on 2017/5/4.
 */

public class PictureCollectViewHolder extends BaseViewHolder {
    ImageView mImageView;

    public ImageView getImageView() {
        return mImageView;
    }

    public PictureCollectViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        super(itemView, onItemClickListener);
        mImageView = ViewUtils.findView(itemView, R.id.adapter_picture_collect_iv);
    }
}
